package com.fcannizzaro.jsoup.annotations;

import com.fcannizzaro.jsoup.annotations.exceptions.AttributeNotFoundException;
import com.fcannizzaro.jsoup.annotations.exceptions.ElementNotFoundException;
import com.fcannizzaro.jsoup.annotations.interfaces.AfterBind;
import com.fcannizzaro.jsoup.annotations.interfaces.Attr;
import com.fcannizzaro.jsoup.annotations.interfaces.Child;
import com.fcannizzaro.jsoup.annotations.interfaces.ForEach;
import com.fcannizzaro.jsoup.annotations.interfaces.Html;
import com.fcannizzaro.jsoup.annotations.interfaces.Items;
import com.fcannizzaro.jsoup.annotations.interfaces.Selector;
import com.fcannizzaro.jsoup.annotations.interfaces.Text;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francesco Cannizzaro (fcannizzaro)
 */

public class JsoupProcessor {

    /**
     * Extract first element according to a query
     */
    private static Element element(Element container, String query, boolean optional) {

        Elements select = container.select(query);

        if (select.size() == 0) {
            if (!optional) throw new ElementNotFoundException(query);
            return null;
        }
        return select.first();
    }

    private static Element element(Element container, String query, int index, boolean optional) {

        Elements select = container.select(query);

        if (select.size() == 0) {
            if (!optional) throw new ElementNotFoundException(query);
            return null;
        }
        return select.get(index);
    }

    private static Object valueOf(Element container, AnnotatedElement field) {

        Selector selector = field.getAnnotation(Selector.class);
        Text text = field.getAnnotation(Text.class);
        Child child = field.getAnnotation(Child.class);
        Items items = field.getAnnotation(Items.class);
        Html html = field.getAnnotation(Html.class);
        Attr attr = field.getAnnotation(Attr.class);

        Object value = null;
        Element el;

        if (field instanceof Field) {

            Field f = (Field) field;

            if (items != null) {

                ParameterizedType type = (ParameterizedType) f.getGenericType();
                Class<?> cz = (Class<?>) type.getActualTypeArguments()[0];
                return fromList(container, cz);

            } else if (child != null) {

                Class cz = f.getType();
                Selector sel = (Selector) cz.getAnnotation(Selector.class);

                if (sel != null) {
                    return from(element(container, sel.value(), child.optional()), cz);
                }

            }

        }

        if (selector != null) {

            return element(container, selector.value(), selector.optional());

        } else if (text != null) {

            el = element(container, text.value(), text.optional());

            if (el != null) {
                return el.text();
            }

        } else if (html != null) {
            if (html.index() != -1) {
                el = element(container, html.value(), html.index(), html.optional());
            } else {
                el = element(container, html.value(), html.optional());
            }
            if (el != null) {
                return el;
            }

        } else if (attr != null) {

            el = element(container, attr.query(), attr.optional());

            if (el != null) {

                value = el.attr(attr.attr());

                if (!attr.optional() && value == null) {
                    throw new AttributeNotFoundException(attr.attr());
                }

            }

        }

        return value;

    }


    /**
     * Bind a Jsoup element to a class
     *
     * @param container dom element
     * @param clazz     object class
     * @param instance     object instance
     */
    public static <T> void from(Element container, Class<T> clazz, T instance) {
        try {
            for (Field field : clazz.getDeclaredFields()) {
                Object value = valueOf(container, field);

                if (value != null) {
                    field.setAccessible(true);
                    field.set(instance, value);
                }
            }

            Method afterBindMethod = null;

            for (Method method : clazz.getDeclaredMethods()) {

                ForEach forEach = method.getAnnotation(ForEach.class);
                AfterBind afterBind = method.getAnnotation(AfterBind.class);

                method.setAccessible(true);

                Object value = valueOf(container, method);

                if (value != null) {

                    method.invoke(instance, value);

                } else if (afterBind != null) {

                    afterBindMethod = method;

                } else if (forEach != null) {

                    Elements elements = container.select(forEach.value());

                    for (int i = 0; i < elements.size(); i++) {

                        Element element = elements.get(i);

                        if (method.getParameterTypes().length > 1) {
                            method.invoke(instance, element, i);
                            continue;
                        }

                        method.invoke(instance, element);
                    }
                }
            }

            if (afterBindMethod != null) {
                afterBindMethod.invoke(instance);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Bind a Jsoup element to a class
     *
     * @param container dom element
     * @param clazz     object class
     * @return object instance
     */
    public static <T> T from(Element container, Class<T> clazz) {
        try{
            T instance = clazz.newInstance();
            from(container, clazz, instance);
            return instance;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Bind multiple object. (Internally use from)
     */
    public static <T> List<T> fromList(Element container, Class<T> clazz) {

        ArrayList<T> items = new ArrayList<>();

        Selector selector = clazz.getAnnotation(Selector.class);

        if (selector != null) {

            Elements elements = container.select(selector.value());

            for (Element element : elements) {
                items.add(from(element, clazz));
            }

        }

        return items;

    }

}
