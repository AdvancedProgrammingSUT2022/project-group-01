package model.resource;

import model.civilization.Civilization;

import java.util.List;
import java.util.Vector;

public class ResourceList {
    private final List<ResourceType> resources;

    public ResourceList(ResourceType... resource) {
        this.resources = List.of(resource);
    }

    public ResourceList(Vector<ResourceType> resources){
        this.resources = new Vector<>();
        for(ResourceType resourceType : resources){
            this.resources.add(resourceType);
        }
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
