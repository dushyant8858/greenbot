package greenbot.provider.aws.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import greenbot.provider.model.Compute;
import greenbot.provider.service.ComputeService;
import lombok.AllArgsConstructor;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.DescribeInstancesResponse;
import software.amazon.awssdk.services.ec2.model.Instance;
import software.amazon.awssdk.services.ec2.model.Reservation;
import software.amazon.awssdk.services.ec2.paginators.DescribeInstancesIterable;

@AllArgsConstructor
@Service("awsComputeService")
public class AwsComputeService implements ComputeService {

	private RegionService regionService;
	private ConversionService conversionService;

	@Override
	@Cacheable("AwsComputeService")
	public List<Compute> list() {
		return regionService.regions()
				.stream()
				.map(region -> Ec2Client.builder().region(region).build())
				.map(Ec2Client::describeInstancesPaginator)
				.flatMap(DescribeInstancesIterable::stream)
				.map(DescribeInstancesResponse::reservations)
				.flatMap(Collection::stream)
				.map(Reservation::instances)
				.flatMap(Collection::stream)
				.map(this::convert)
				.collect(Collectors.toList());
	}

	private Compute convert(Instance instance) {
		return conversionService.convert(instance, Compute.class);
	}
}
