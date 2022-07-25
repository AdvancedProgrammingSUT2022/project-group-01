package model.resource;

import model.civilization.Civilization;

import java.util.List;

public class ResourceList {
    private final List<ResourceType> resources;

    public ResourceList(ResourceType... resource) {
        this.resources = List.of(resource);
    }

    public boolean isAvailable(Civilization civilization) {
        for (ResourceType resource : resources) {
            if(!civilization.getResourceRepository().containsKey(resource)
                    || civilization.getResourceRepository().get(resource) == 0)
                return false;
        }
        return true;
    }
}
