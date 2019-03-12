package com.djdu.Manage.repository;

import com.djdu.Manage.entity.Manage;
import com.djdu.brand.entity.Brand;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @InterfaceName ManageRepository
 * @Description TODO 仓库层，进行对数据库的各种操作，逻辑删除故删除标记量为未删除，0为未删除,管理员状态1为未禁用
 * @Author DJDU
 * @Date 2019/3/11 10:05
 * @Version 1.0
 **/
@Repository
public interface ManageRepository extends PagingAndSortingRepository<Manage,String>, JpaSpecificationExecutor<Manage> {
   /**
    * @Author DJDU
    * @Description TODO 通过名字查找表中是否存在同名的未删除的用户名
    * @Date 2019/3/11 11:10
    * @Param [name]
    * @return java.lang.Integer
    **/
    @Query(value = "select count(*) from manage a where a.name = ?1 AND a.usable = 0", nativeQuery = true)
    Integer UnDeletedfindExistsName(String name);

    /**
     * @Author DJDU
     * @Description TODO 通过名字查找未删除的状态为可用的用户的密码
     * @Date 2019/3/11 11:11
     * @Param [name]
     * @return java.lang.String
     **/
    @Query(value = "select password from manage a where a.name = ?1 AND a.usable = 0 AND a.state = 1", nativeQuery = true)
    String  UnDeletedfindAndStatefindByNameToLogin(String name);


    /**
     * @Author DJDU
     * @Description TODO 同名字查找是否存在未删除的同名的管理员
     * @Date 2019/3/12 11:00
     * @Param [name]
     * @return java.lang.Integer
     **/
    @Query(value = "select count(*) from manage a where a.name = ?1 AND a.usable = 0", nativeQuery = true)
    Integer  UnDeletedfindByNameToLoginExists(String name);

    /**
     * @Author DJDU
     * @Description TODO 通过名字查找是否存在未删除的状态为可用的用户
     * @Date 2019/3/11 11:13 
     * @Param [name]
     * @return java.lang.Integer
     **/
    @Query(value = "select count(*) from manage a where a.name = ?1 AND a.usable = 0 AND a.state = 1", nativeQuery = true)
    Integer  UnDeletedfindAndStatefindByNameToLoginExists(String name);

    /**
     * @Author DJDU
     * @Description TODO 通过名字查找未删除的状态为可用的用户
     * @Date 2019/3/12 11:12
     * @Param [name]
     * @return java.util.Optional<com.djdu.Manage.entity.Manage>
     **/
    @Query(value = "select * from manage a where a.name = ?1 AND a.usable = 0 AND a.state = 1", nativeQuery = true)
    Optional<Manage> findByName(String  name);


}
