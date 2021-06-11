package com.sumansoul.common.arounter

import android.content.Context
import android.net.Uri
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.service.PathReplaceService
import java.util.*

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2021/1/19
 * Time: 11:33
 */
@Route(path = PathReplaceServiceImpl.PATH)
public class PathReplaceServiceImpl : PathReplaceService{
    private  var pathMap: MutableMap<String, String>?=null
    public companion object{
        const val PATH = "/service/PathReplaceServiceImpl"
    }
    override fun forString(path: String): String {
        if(pathMap?.containsKey(path)!=null){
            return pathMap?.get(path)?:path
        }
        return path
    }

    override fun forUri(uri: Uri): Uri { return uri
    }

    override fun init(context: Context) {
        pathMap = HashMap()
    }
    fun replacePath(sourcePath: String, targetPath: String) {
        pathMap?.put(sourcePath, targetPath)
    }
}