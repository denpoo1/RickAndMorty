/**
 * Created by Denys Durbalov
 * Date of creation: 5/30/24
 * Project name: RickAndMorty
 * email: den.dyrbalov25@gmail.com or s28680@pjwstk.edu.pl
 */

package rickmorty.rickandmorty;

import com.google.gson.*;
import org.hibernate.proxy.HibernateProxy;

import java.lang.reflect.Type;

public class HibernateProxyTypeAdapter implements JsonSerializer<HibernateProxy> {

    @Override
    public JsonElement serialize(HibernateProxy src, Type typeOfSrc, JsonSerializationContext context) {
        return context.serialize(src.getHibernateLazyInitializer().getImplementation());
    }

    public static GsonBuilder registerHibernateProxyTypeAdapter(GsonBuilder builder) {
        builder.registerTypeHierarchyAdapter(HibernateProxy.class, new HibernateProxyTypeAdapter());
        return builder;
    }
}

