# ThreeLayersNestedScrolling
参考Demo： https://github.com/hufeiyang/NestedScrollingParent2Layout
# 经过扩展支持双层嵌套，三层嵌套：
   # 1、RecyclerView嵌套RecyclerView
   # 2、RecyclerView嵌套ViewPager嵌套RecyclerView功能
   # 3、RecyclerView嵌套ViewPager嵌套RecyclerView嵌套ViewPager2嵌套RecyclerView功能

# 使用此框架最内部嵌套，如果设置了Padding或者Margin 此Item的高度必须设置MATCH_PARENT，否则还会出现滚动冲突问题。
    # 设置方式如下：
    # helper.itemView.getLayoutParams().height = isLastItem ? RecyclerView.LayoutParams.MATCH_PARENT : RecyclerView.LayoutParams.WRAP_CONTENT;



