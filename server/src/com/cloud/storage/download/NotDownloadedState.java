// Licensed to the Apache Software Foundation (ASF) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The ASF licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.
package com.cloud.storage.download;

import com.cloud.agent.api.storage.DownloadProgressCommand.RequestType;
import com.cloud.storage.VMTemplateStorageResourceAssoc.Status;

public class NotDownloadedState extends DownloadActiveState {


	public NotDownloadedState(DownloadListener downloadListener) {
		super(downloadListener);
	}

	

	@Override
	public String getName() {
		return Status.NOT_DOWNLOADED.toString();
	}



	@Override
	public void onEntry(String prevState, DownloadEvent event, Object evtObj) {
		super.onEntry(prevState, event, evtObj);
		getDownloadListener().scheduleStatusCheck(RequestType.GET_STATUS);
	}



}
