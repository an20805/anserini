# Anserini Regressions: Mr. TyDi (v1.1) &mdash; Swahili

**Models**: bag-of-words approaches using `AutoCompositeAnalyzer`

This page documents BM25 regression experiments for [Mr. TyDi (v1.1) &mdash; Swahili](https://github.com/castorini/mr.tydi) using `AutoCompositeAnalyzer`.

The exact configurations for these regressions are stored in [this YAML file](../src/main/resources/regression/mrtydi-v1.1-sw-aca.yaml).
Note that this page is automatically generated from [this template](../src/main/resources/docgen/templates/mrtydi-v1.1-sw-aca.template) as part of Anserini's regression pipeline, so do not modify this page directly; modify the template instead.

From one of our Waterloo servers (e.g., `orca`), the following command will perform the complete regression, end to end:

```
python src/main/python/run_regression.py --index --verify --search --regression mrtydi-v1.1-sw-aca
```

## Indexing

Typical indexing command:

```
target/appassembler/bin/IndexCollection \
  -collection MrTyDiCollection \
  -input /path/to/mrtydi-v1.1-sw \
  -index indexes/lucene-index.mrtydi-v1.1-swahili-aca/ \
  -generator DefaultLuceneDocumentGenerator \
  -threads 1 -storePositions -storeDocvectors -storeRaw -language sw -useAutoCompositeAnalyzer \
  >& logs/log.mrtydi-v1.1-sw &
```

See [this page](https://github.com/castorini/mr.tydi) for more details about the Mr. TyDi corpus.
For additional details, see explanation of [common indexing options](common-indexing-options.md).

## Retrieval

After indexing has completed, you should be able to perform retrieval as follows:

```
target/appassembler/bin/SearchCollection \
  -index indexes/lucene-index.mrtydi-v1.1-swahili-aca/ \
  -topics tools/topics-and-qrels/topics.mrtydi-v1.1-sw.train.txt.gz \
  -topicreader TsvInt \
  -output runs/run.mrtydi-v1.1-sw.bm25.topics.mrtydi-v1.1-sw.train.txt \
  -bm25 -hits 100 -language sw -useAutoCompositeAnalyzer &
target/appassembler/bin/SearchCollection \
  -index indexes/lucene-index.mrtydi-v1.1-swahili-aca/ \
  -topics tools/topics-and-qrels/topics.mrtydi-v1.1-sw.dev.txt.gz \
  -topicreader TsvInt \
  -output runs/run.mrtydi-v1.1-sw.bm25.topics.mrtydi-v1.1-sw.dev.txt \
  -bm25 -hits 100 -language sw -useAutoCompositeAnalyzer &
target/appassembler/bin/SearchCollection \
  -index indexes/lucene-index.mrtydi-v1.1-swahili-aca/ \
  -topics tools/topics-and-qrels/topics.mrtydi-v1.1-sw.test.txt.gz \
  -topicreader TsvInt \
  -output runs/run.mrtydi-v1.1-sw.bm25.topics.mrtydi-v1.1-sw.test.txt \
  -bm25 -hits 100 -language sw -useAutoCompositeAnalyzer &
```

Evaluation can be performed using `trec_eval`:

```
tools/eval/trec_eval.9.0.4/trec_eval -c -M 100 -m recip_rank -c -m recall.100 tools/topics-and-qrels/qrels.mrtydi-v1.1-sw.train.txt runs/run.mrtydi-v1.1-sw.bm25.topics.mrtydi-v1.1-sw.train.txt
tools/eval/trec_eval.9.0.4/trec_eval -c -M 100 -m recip_rank -c -m recall.100 tools/topics-and-qrels/qrels.mrtydi-v1.1-sw.dev.txt runs/run.mrtydi-v1.1-sw.bm25.topics.mrtydi-v1.1-sw.dev.txt
tools/eval/trec_eval.9.0.4/trec_eval -c -M 100 -m recip_rank -c -m recall.100 tools/topics-and-qrels/qrels.mrtydi-v1.1-sw.test.txt runs/run.mrtydi-v1.1-sw.bm25.topics.mrtydi-v1.1-sw.test.txt
```

## Effectiveness

With the above commands, you should be able to reproduce the following results:

| **MRR@100**                                                                                                  | **BM25**  |
|:-------------------------------------------------------------------------------------------------------------|-----------|
| [Mr. TyDi (Swahili): train](https://github.com/castorini/mr.tydi)                                            | 0.2997    |
| [Mr. TyDi (Swahili): dev](https://github.com/castorini/mr.tydi)                                              | 0.2928    |
| [Mr. TyDi (Swahili): test](https://github.com/castorini/mr.tydi)                                             | 0.4257    |
| **R@100**                                                                                                    | **BM25**  |
| [Mr. TyDi (Swahili): train](https://github.com/castorini/mr.tydi)                                            | 0.7020    |
| [Mr. TyDi (Swahili): dev](https://github.com/castorini/mr.tydi)                                              | 0.6984    |
| [Mr. TyDi (Swahili): test](https://github.com/castorini/mr.tydi)                                             | 0.8396    |