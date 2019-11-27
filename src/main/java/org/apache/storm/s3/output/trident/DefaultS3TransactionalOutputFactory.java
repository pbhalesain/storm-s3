/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.storm.s3.output.trident;

import org.apache.storm.s3.output.UploaderFactory;
import org.apache.storm.s3.output.Uploader;

import java.util.Map;

public class DefaultS3TransactionalOutputFactory<T> implements S3TransactionalOutputFactory<T> {

    private Uploader transferManager;

    public S3TransactionalOutput build(T key, Map conf, FileOutputFactory fileOutputFactory) {
        return new DefaultS3TransactionalOutput(key, conf, getUploader(conf), fileOutputFactory);
    }

    private Uploader getUploader(Map conf) {
        if (transferManager == null) {
            transferManager = UploaderFactory.buildUploader(conf);
        }
        return transferManager;
    }
}
