#!/usr/bin/env python3
"""
scale_arrays.py
===============

Shrinks the @ARRAY(...) length distributions in one or more arr_*.csv.gz
files produced by gen_datagen_csvs.py, in order to reduce the average
generated document size.

For each target file, every observed length L (with its probability/count)
is rescaled by a factor towards a desired new weighted-average length, then
rounded to the nearest non-negative integer. Rows that collapse onto the
same new length after rounding have their counts summed.

Usage:
    python3 scale_arrays.py <zillow_dir>
"""
import csv
import gzip
import io
import os
import re
import sys
from collections import Counter

ARRAY_RE = re.compile(r'^(.*,)(\d+)(\))$')


def weighted_avg(rows):
    tot = 0
    totc = 0
    for name, n, c in rows:
        tot += n * c
        totc += c
    return (tot / totc) if totc else 0


def read_arr_csv(path):
    with gzip.open(path, "rt", encoding="utf-8", newline="") as f:
        reader = csv.reader(f)
        header = next(reader)
        rows = []
        for row in reader:
            directive, count = row[0], row[-1]
            m = ARRAY_RE.match(directive)
            if not m:
                raise ValueError("Unrecognized @ARRAY directive: {}".format(directive))
            prefix, n, suffix = m.group(1), int(m.group(2)), m.group(3)
            rows.append((prefix, n, suffix, float(count)))
    return header, rows


def write_arr_csv(path, header, rows):
    buf = io.StringIO()
    writer = csv.writer(buf, quoting=csv.QUOTE_MINIMAL)
    writer.writerow(header)
    for prefix, n, suffix, count in rows:
        directive = "{}{}{}".format(prefix, n, suffix)
        count_out = int(count) if float(count).is_integer() else count
        writer.writerow([directive, count_out])
    with gzip.open(path, "wt", encoding="utf-8", newline="") as gz:
        gz.write(buf.getvalue())


def scale_file(path, target_avg):
    header, rows = read_arr_csv(path)
    cur_avg = weighted_avg([(p, n, c) for p, n, s, c in rows])
    if cur_avg == 0:
        print("  SKIP {} (current avg is 0)".format(path))
        return
    # Preserve "never zero" semantics: if the original distribution never
    # observed a length of 0 (e.g. every listing had at least 1 photo),
    # don't let rounding introduce 0-length arrays - clamp the minimum to 1
    # instead. If 0 was already a legitimate observed length, allow it.
    min_len = 0 if any(n == 0 for _, n, _, _ in rows) else 1
    factor = target_avg / cur_avg
    merged = Counter()
    prefix0 = rows[0][0]
    suffix0 = rows[0][2]
    for prefix, n, suffix, count in rows:
        new_n = max(min_len, round(n * factor))
        merged[new_n] += count
    new_rows = [(prefix0, n, suffix0, c) for n, c in sorted(merged.items())]
    new_avg = weighted_avg([(p, n, c) for p, n, s, c in new_rows])
    write_arr_csv(path, header, new_rows)
    print("  {}: avg {:.3f} -> {:.3f} (target {:.3f})".format(path, cur_avg, new_avg, target_avg))


def main():
    if len(sys.argv) != 2:
        print("Usage: python3 scale_arrays.py <zillow_dir>")
        sys.exit(1)
    root = sys.argv[1]

    # path (relative to root) -> desired new weighted-average array length
    targets = {
        "arr_photos.csv.gz": 3.5,
        "arr_nearbyHomes.csv.gz": 3.0,
        "arr_priceHistory.csv.gz": 3.0,
        "arr_taxHistory.csv.gz": 8.0,
        "homeValuation/arr_comps.csv.gz": 2.0,
        "photos/arr_jpeg.csv.gz": 3.0,
        "homeValuation/comps/compsCarouselPropertyPhotos/arr_jpeg.csv.gz": 3.0,
    }

    print("Scaling array-length distributions under {} ...".format(root))
    for rel, target in targets.items():
        path = os.path.join(root, rel)
        if not os.path.exists(path):
            print("  MISSING {} (skipped)".format(path))
            continue
        scale_file(path, target)
    print("Done.")


if __name__ == "__main__":
    main()
