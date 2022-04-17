package Resource;

import java.util.List;

public class ResourceList {
	private List<ResourceType> resources;

	public ResourceList(ResourceType... resource) {
		this.resources = List.of(resource);
	}

	public boolean isResearched() {
		return true;
	}
}
