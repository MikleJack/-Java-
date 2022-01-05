package com.example.demo.controller;

import com.example.demo.entity.DepTree;
import com.example.demo.service.DepTreeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (DepTree)表控制层
 *
 * @author makejava
 * @since 2022-01-05 15:20:46
 */
@RestController
@RequestMapping("depTree")
public class DepTreeController {
    /**
     * 服务对象
     */
    @Resource
    private DepTreeService depTreeService;

    /**
     * 分页查询
     *
     * @param depTree 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<DepTree>> queryByPage(DepTree depTree, PageRequest pageRequest) {
        return ResponseEntity.ok(this.depTreeService.queryByPage(depTree, pageRequest));
    }

//    部门树形结构查询
    @GetMapping("/cateList")
    public List<DepTree> cateList() {

        // 整体思路：
        // 1、取得所有数据、放入集合List1 （tbCategories）
        // 2、将List1所有数据都放入到map（treeMap）中：元素id为键，元素本身对象为值
        // 3、取得顶层节点放入集合List2中（resultList）
        // 4、遍历List1中的所有数据，通过数据的parentId为键在map中取值
        //      1）如果能取到，则说明该元素有父节点
        //           1、判断该父节点下的childList中是否有已经子节点
        //              1、若无：则创建一个集合，将子节点放入
        //              2、若有：则直接将子节点放入即可
        // 5、把放好的数据放回到map中
        // 6、返回List2（resultList）

        // 注意：整个过程将所有数据取出放入list2（resultList）,返回的也是    //list2


        List<DepTree> tbCategories = depTreeService.cateList();

        List<DepTree> resultList = new ArrayList<DepTree>(); // 存贮顶层的数据

        Map<Object ,Object> treeMap = new HashMap();
        Object itemTree;

        for(int i = 0;i<tbCategories.size() && !tbCategories.isEmpty();i++){
            itemTree = tbCategories.get(i);
            treeMap.put(tbCategories.get(i).getDepNum(),tbCategories.get(i));// 把所有的数据都放到map中
        }


// 这里也可以用另一种方法，就是拿到集合里的每个元素的父id去数据库中查询，但是，这样与数据库的交互次数就太多了
        // 遍历map得到顶层节点（游离节点也算作顶层节点）
        for(int i =0;i<tbCategories.size();i++){
            // 优点1：整个方法，只查询了一次数据库
            // 优点2：不用知道顶层节点的id
            if(!treeMap.containsKey(tbCategories.get(i).getDepNumSuperior())){
                // 我们在存储的时候就是将元素的id为键，元素本身为值存入的
                // 以元素的父id为键，在map里取值，若取不到则，对应的元素不存在，即没有父节点，为顶层节点或游离节点
                // 将顶层节点放入list集合
                resultList.add(tbCategories.get(i));
            }
        }

        // 循环数据，将数据放到该节点的父节点的children属性中
        for(int i =0 ;i<tbCategories.size()&& !tbCategories.isEmpty();i++){
            // 数据库中，若一个元素有子节点，那么，该元素的id为子节点的父id
            //treeMap.get(tbCategories.get(i).getParentId()); // 从map集合中找到父节点
            DepTree category = (DepTree)treeMap.get(tbCategories.get(i).getDepNumSuperior());
            if(category!=null ){ // 不等于null，也就意味着有父节点
                // 有了父节点，要判断父节点下存贮字节点的集合是否存在，然后将子节点放入
//                if(category.getDepNumJunior() == null){
//                    // 判断一个集合是否被创建用null：表示结合还没有被分配内存空间(即还没有被创建)，内存大小自然为null
//                    // 用集合的size判断集合中是否有元素，为0，没有元素（集合已经被创建），
//                    category.setChildList(new ArrayList<DepTree>());
//                }
                category.getChildList().add(tbCategories.get(i)); // 添加到父节点的ChildList集合下

                // 这一步其实可以不要，因为我们修改了数据（添加了子节点，然后在将元素放入到map中，
                // 若键相同，map会自动覆盖掉相同的键值对，达到更新map集合中的数据的目的），但是我们
                // 这里只是从map中取值，而并不关心值的子节点（子节点是对象本身自己封装的。这里我们知道
                // 元素从查询后放入map,父节点放入list，然后通过键来在map中取得对象，之后再将修改过的对象重新放入map当中
                // ,我们并没有直接操作list,但是在list中对象的值却是已经修改过了，这就是对象的引用传递，同一个引用对象是通过
                // 地址值来操作对象的，即有不同的引用，但是对象中的属性是已经通过引用的操作而改变的，所以这里一旦修改过后，无论是map中还是list中，再次取值时都已经是更改过后的值了）
                treeMap.put(tbCategories.get(i).getDepNumSuperior(),category);  // 把放好的数据放回到map中
            }

        }


        return resultList;
    }

}

