<template>
    <ul class="menus">
      <li v-for="item in menus">
        <a :href="`#${item.id}`">{{item.title}}</a>
        <ul v-if="item[childLabel]" class="child">
          <menu-tree :menus="item[childLabel]" :child-label="childLabel"></menu-tree>
        </ul>
      </li>
    </ul>

</template>

<script>
    export default {
        name: "menuTree",
        props: {
            menus: {
                type: [Object,Array],
                required: true
            },
            childLabel: {
                type: String,
                default: 'child'
            }
        },
        data() {
            return{
            }
        },
        methods: {
        }
    }
</script>

<style scoped lang="less">
    .menus{
        line-height: 30px;
    }
    .child{
        padding-left: 15px;
    }
    li{
      -webkit-line-clamp: 1;
      display: -webkit-box;
      -webkit-box-orient: vertical;
      overflow: hidden;
      text-overflow: ellipsis;
    }
    ul{
      -webkit-line-clamp: 1;
      display: -webkit-box;
      -webkit-box-orient: vertical;
      overflow: hidden;
      text-overflow: ellipsis;
    }

</style>
