package model.chatroom;

import com.thoughtworks.xstream.XStream;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter @Setter
public class Query {

    private String header;
    private HashMap<String, Object> values = new HashMap<>();

    public Query(String header){
        this.header = header;
    }

    public void put(String title, Object value){
        this.values.put(title, value);
    }

    public Object get(String key){
        return values.get(key);
    }

    public String toJson(){
        XStream xStream = new XStream();
        xStream.setMode(XStream.ID_REFERENCES);
        return xStream.toXML(this);
    }


    @Override
    public String toString(){
        return toJson();
    }

    public static Query fromJson(String json){
        XStream xStream = new XStream();
        xStream.setMode(XStream.ID_REFERENCES);
        return (Query) xStream.fromXML(json);
    }

}
