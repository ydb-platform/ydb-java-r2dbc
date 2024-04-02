/*
 * Copyright 2022 YANDEX LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tech.ydb.io.r2dbc.state;

import reactor.core.publisher.Mono;
import tech.ydb.io.r2dbc.YdbContext;
import tech.ydb.table.Session;
import tech.ydb.table.transaction.TxControl;

/**
 * @author Egor Kuleshov
 */
public class OutsideTransactionState extends AutoCommitState implements YdbConnectionState {

    public OutsideTransactionState(YdbContext ydbContext, YdbTxSettings ydbTxSettings) {
        super(ydbContext, ydbTxSettings);
    }

    @Override
    public Mono<Session> getSession() {
        return Mono.error(new IllegalStateException("Transaction not started"));
    }

    @Override
    public TxControl<?> txControl() {
        throw new IllegalStateException("Transaction not started");
    }
}
