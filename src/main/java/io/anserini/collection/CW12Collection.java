package io.anserini.collection;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import io.anserini.document.ClueWeb12WarcRecord;
import io.anserini.document.SourceDocument;

import java.io.IOException;

public class CW12Collection<D extends ClueWeb12WarcRecord> extends WarcCollection {
  public CW12Collection() throws IOException {
    super();
  }

  @Override
  public SourceDocument next() {
    ClueWeb12WarcRecord doc = new ClueWeb12WarcRecord();
    try {
      doc = (D)doc.readNextWarcRecord(inStream, ClueWeb12WarcRecord.WARC_VERSION);
      if (doc == null) {
        at_eof = true;
        doc = null;
      }
    } catch (IOException e1) {
      doc = null;
    }
    return doc;
  }
}