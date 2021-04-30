<v-navigation-drawer
        app
        permanent>
    <v-list-item>
        <v-list-item-content>
            <v-list-item-title class="title">
                起名工具
            </v-list-item-title>
            <v-list-item-subtitle>
                按照五行属性生成名字
            </v-list-item-subtitle>
        </v-list-item-content>
    </v-list-item>

    <v-divider></v-divider>

    <v-list
            dense
            nav
    >
        <v-list-item
                link
                href="/single-character"
        >
            <v-list-item-icon>
                <v-icon>mdi-at</v-icon>
            </v-list-item-icon>
            <v-list-item-content>
                <v-list-item-title>字库上传</v-list-item-title>
            </v-list-item-content>
        </v-list-item>
        <v-list-item
                link
                href="/single-character-manage"
                active
        >
            <v-list-item-icon>
                <v-icon>mdi-google-assistant</v-icon>
            </v-list-item-icon>
            <v-list-item-content>
                <v-list-item-title>字库管理</v-list-item-title>
            </v-list-item-content>
        </v-list-item>
        <v-list-item
                link
                href="/name-library"
                active
        >
            <v-list-item-icon>
                <v-icon>mdi-google-circles</v-icon>
            </v-list-item-icon>
            <v-list-item-content>
                <v-list-item-title>名库上传</v-list-item-title>
            </v-list-item-content>
        </v-list-item>
        <v-list-item
                link
                href="/name-library-manage"
                active
        >
            <v-list-item-icon>
                <v-icon>mdi-animation</v-icon>
            </v-list-item-icon>
            <v-list-item-content>
                <v-list-item-title>名库管理</v-list-item-title>
            </v-list-item-content>
        </v-list-item>
        <v-list-item
                link
                href="/order"
                active
        >
            <v-list-item-icon>
                <v-icon>mdi-blur-linear</v-icon>
            </v-list-item-icon>
            <v-list-item-content>
                <v-list-item-title>添加订单</v-list-item-title>
            </v-list-item-content>
        </v-list-item>
        <v-list-item
                link
                href="/order/list"
                active
        >
            <v-list-item-icon>
                <v-icon>mdi-view-list</v-icon>
            </v-list-item-icon>
            <v-list-item-content>
                <v-list-item-title>订单列表</v-list-item-title>
            </v-list-item-content>
        </v-list-item>
<#--        <v-list-item-->
<#--                link-->
<#--                href="/customer-info"-->
<#--                active-->
<#--        >-->
<#--            <v-list-item-icon>-->
<#--                <v-icon>mdi-account-multiple-plus-outline</v-icon>-->
<#--            </v-list-item-icon>-->
<#--            <v-list-item-content>-->
<#--                <v-list-item-title>客户信息录入</v-list-item-title>-->
<#--            </v-list-item-content>-->
<#--        </v-list-item>-->
<#--        <v-list-item-->
<#--                link-->
<#--                href="/customer-info/list"-->
<#--                active-->
<#--        >-->
<#--            <v-list-item-icon>-->
<#--                <v-icon>mdi-account-outline</v-icon>-->
<#--            </v-list-item-icon>-->
<#--            <v-list-item-content>-->
<#--                <v-list-item-title>客户列表</v-list-item-title>-->
<#--            </v-list-item-content>-->
<#--        </v-list-item>-->
    </v-list>

</v-navigation-drawer>