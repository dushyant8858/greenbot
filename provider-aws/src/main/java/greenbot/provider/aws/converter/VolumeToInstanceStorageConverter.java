/*
 * Copyright 2020 Vinay Lodha (https://github.com/vinay-lodha)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package greenbot.provider.aws.converter;

import static java.util.stream.Collectors.toMap;

import java.util.Map;
import java.util.function.Function;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import greenbot.rule.model.cloud.InstanceStorage;
import greenbot.rule.model.cloud.Tag;
import lombok.AllArgsConstructor;
import software.amazon.awssdk.services.ec2.model.Volume;

/**
 * 
 * @author Vinay Lodha
 */
@Component
@AllArgsConstructor
public class VolumeToInstanceStorageConverter implements Converter<Volume, InstanceStorage> {

	private final Ec2TagToTagConverter ec2TagToTagConverter;

	@Override
	public InstanceStorage convert(Volume source) {
		Map<String, Tag> tags = source.tags()
				.stream()
				.map(ec2TagToTagConverter::convert)
				.collect(toMap(Tag::getKey, Function.identity()));

		return InstanceStorage.builder()
				.id(source.volumeId())
				.tags(tags)
				.build();
	}

}