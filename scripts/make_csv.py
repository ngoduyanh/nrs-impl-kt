import unicodecsv as csv
import json
import sys
# Change this to change the CSV format
labels = [
    "ID",
    "Title",
    "MAL normalized score",
    "Overall",
    "AU",
    "AP",
    "MU",
    "MP",
    "CU",
    "CP",
    "AL",
    "AV",
    "AM",
    "B",
    "A"
]


def output(id, entry, score): return [
    id,
    entry['DAH_meta']['DAH_entry_title'],
    score['DAH_meta']['DAH_anime_normalize']['score'],
    score['DAH_meta']['DAH_overall_score'],
] + score['overallVector']

w = csv.writer(open('output/nrs.csv', 'wb'))
if sys.version_info < (3, 0):
    from codecs import open
w.writerow(labels)
entries = json.load(open('output/entries.json', encoding='utf-8'))
scores = json.load(open('output/scores.json', encoding='utf-8'))
for id, entry in entries.items():
    w.writerow(output(id, entry, scores[id]))
