<v-navigation-drawer
        app
        permanent>
    <v-list-item>
        <v-list-item-content>
            <v-list-item-title class="title">
                玖言国学起名系统
            </v-list-item-title>
            <v-list-item-subtitle>
                仅限内部使用
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
        <v-list-item
                link
                href="/customer-info"
                active
        >
            <v-list-item-icon>
                <v-icon>mdi-account-multiple-plus-outline</v-icon>
            </v-list-item-icon>
            <v-list-item-content>
                <v-list-item-title>回访对象录入</v-list-item-title>
            </v-list-item-content>
        </v-list-item>
        <v-list-item
                link
                href="/customer-info/list"
                active
        >
            <v-list-item-icon>
                <v-badge
                        color="green"
                        :content="visitCnt"
                        :value="visitCnt>0"
                        offset-x="5"
                        offset-y="15"
                >
                    <v-icon>mdi-account-outline</v-icon>
                </v-badge>
            </v-list-item-icon>
            <v-list-item-content>
                <v-list-item-title>应回访列表</v-list-item-title>
            </v-list-item-content>
        </v-list-item>
        <v-list-item
                link
                href="/order/list/delivering"
                active
        >
            <v-list-item-icon>
                <v-icon>mdi-view-agenda</v-icon>
            </v-list-item-icon>
            <v-list-item-content>
                <v-list-item-title>待交付列表</v-list-item-title>
            </v-list-item-content>
        </v-list-item>
    </v-list>

</v-navigation-drawer>