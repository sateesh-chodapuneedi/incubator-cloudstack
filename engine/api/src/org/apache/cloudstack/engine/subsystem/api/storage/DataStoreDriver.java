/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.cloudstack.engine.subsystem.api.storage;

import com.cloud.agent.api.Answer;
import com.cloud.agent.api.Command;

public interface DataStoreDriver {
	String getDriverType();
	TemplateProfile install(TemplateProfile tp, DataStoreEndPoint ep);
	TemplateProfile register(TemplateProfile tp, DataStoreEndPoint ep);
	DataObject create(DataObject obj);
	DataObject copy(DataObject src, DataStore dest);
	DataObject copy(DataObject src, DataObject dest);
	DataObject move(DataObject src, DataObject dest);
	VolumeProfile createVolumeFromTemplate(VolumeProfile vol, TemplateProfile tp, DataStoreEndPoint dp);
	Answer sendMessage(DataStoreEndPoint dsep, Command cmd);
	boolean delete(DataObject obj);
}