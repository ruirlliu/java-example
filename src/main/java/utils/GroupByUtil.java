package utils;

import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @author liurui
 * @date 2021/2/23
 */
public class GroupByUtil {

    public static <T, R> List<T> groupBy(List<T> origin, Function<T, R> mapper) {
        if (CollUtil.isEmpty(origin)) {
            return Collections.emptyList();
        }
        Map<R, List<T>> group = new LinkedHashMap<>(16);
        for (T t : origin) {
            R key = mapper.apply(t);
            group.computeIfAbsent(key, k -> new ArrayList<>()).add(t);
        }
        List<T> change = new ArrayList<>(group.size());
        for (Map.Entry<R, List<T>> entry : group.entrySet()) {
            change.add(entry.getValue().get(0));
        }
        return change;
//        if (CollUtil.isEmpty(origin)) {
//            return Collections.emptyList();
//        }
//        Map<R, List<T>> collect = origin.stream().collect(Collectors.groupingBy(mapper));
//        List<T> change = new ArrayList<>(collect.size());
//        for (Map.Entry<R, List<T>> entry : collect.entrySet()) {
//            change.add(entry.getValue().get(0));
//        }
//        return change;
    }

    public static void main(String[] args) {

        Image image1 = new Image("test01", "1");
        Image image2 = new Image("test01", "2");
        Image image3 = new Image("test01", "3");
        Image image4 = new Image("test01", "4");
        Image image5 = new Image("test02", "4");
        Image image6 = new Image("test02", "4");
        Image image7 = new Image("test03", "4");
        Image image8 = new Image("test04", "4");
        ArrayList<Image> images = Lists.newArrayList(image1, image2, image3, image4, image5, image6, image7, image8);
        List<Image> change = groupBy(images, Image::getImageName);
        System.out.println(change);
    }

}
