<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>XIAO ZHI</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/@mdi/font@3.x/css/materialdesignicons.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/vuetify@2.x/dist/vuetify.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Material+Icons" rel="stylesheet">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" rel="stylesheet"
          crossorigin="anonymous">
    <link href="/main.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, minimal-ui">
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
<div id="app">
    <v-app>
        <#--        <v-card>-->
        <v-navigation-drawer
                absolute
                permanent>
            <v-list-item>
                <v-list-item-content>
                    <v-list-item-title class="title">
                        Application
                    </v-list-item-title>
                    <v-list-item-subtitle>
                        subtext
                    </v-list-item-subtitle>
                </v-list-item-content>
            </v-list-item>

            <v-divider></v-divider>

            <v-list
                    dense
                    nav
            >
                <v-list-item
                        v-for="item in items"
                        :key="item.title"
                        link
                >
                    <v-list-item-icon>
                        <v-icon>{{ item.icon }}</v-icon>
                    </v-list-item-icon>

                    <v-list-item-content>
                        <v-list-item-title>{{ item.title }}</v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
            </v-list>

        </v-navigation-drawer>
        <v-main style="padding: 0px 0px 0px 256px;">
            <v-container>
                <v-row>
                    <v-col
                            xs="12"
                            sm="12"
                            md="6"
                            lg="4"
                    >
                        <v-card
                                class="mx-auto my-12"
                        >
                            <v-img
                                    height="250"
                                    src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
                            ></v-img>

                            <v-card-title>基装</v-card-title>

                            <v-card-text>
                                <v-form
                                        ref="formA"
                                        @submit.prevent="submitA"
                                >
                                    <v-file-input chips label="空白台账" v-model="originFileA"></v-file-input>
                                    <v-file-input small-chips multiple label="分店"
                                                  v-model="dataFilesA"></v-file-input>
                                    <v-btn
                                            text
                                            type="submit"
                                    >
                                        submit
                                    </v-btn>
                                </v-form>
                            </v-card-text>
                        </v-card>
                    </v-col>
                    <v-col
                            xs="12"
                            sm="12"
                            md="6"
                            lg="4"
                    >
                        <v-card
                                class="mx-auto my-12"
                        >
                            <v-img
                                    height="250"
                                    src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"
                            ></v-img>

                            <v-card-title>增项</v-card-title>

                            <v-card-text>
                                <v-form
                                        ref="formB"
                                        @submit.prevent="submitB"
                                >
                                    <v-file-input chips label="增项台账" v-model="originFileB"></v-file-input>
                                    <v-file-input small-chips multiple label="分店"
                                                  v-model="dataFilesB"></v-file-input>
                                    <v-btn
                                            text
                                            type="submit"
                                    >
                                        submit
                                    </v-btn>
                                </v-form>
                            </v-card-text>
                        </v-card>
                    </v-col>
                    <v-col
                            xs="12"
                            sm="12"
                            md="6"
                            lg="4"
                    >
                        <v-card
                                class="mx-auto my-12"
                        >
                            <v-img
                                    height="250"
                                    src="https://cdn.vuetifyjs.com/images/cards/house.jpg"
                            ></v-img>

                            <v-card-title>改造维修</v-card-title>

                            <v-card-text>
                                <v-form
                                        ref="formC"
                                        @submit.prevent="submitC"
                                >
                                    <v-file-input chips label="台账" v-model="originFileC"></v-file-input>
                                    <v-file-input small-chips multiple label="分店"
                                                  v-model="dataFilesC"></v-file-input>
                                    <v-btn
                                            text
                                            type="submit"
                                    >
                                        submit
                                    </v-btn>
                                </v-form>
                            </v-card-text>
                        </v-card>
                    </v-col>
                </v-row>
            </v-container>
        </v-main>
        <#--        </v-card>-->
        <#--        <v-container fluid>-->
        <#--            <v-row>-->
        <#--                <v-col-->
        <#--                        xs="12"-->
        <#--                        sm="12"-->
        <#--                        md="6"-->
        <#--                        lg="4"-->
        <#--                >-->
        <#--                    <v-card-->
        <#--                            class="mx-auto my-12"-->
        <#--                    >-->
        <#--                        <v-img-->
        <#--                                height="250"-->
        <#--                                src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"-->
        <#--                        ></v-img>-->

        <#--                        <v-card-title>基装</v-card-title>-->

        <#--                        <v-card-text>-->
        <#--                            <v-form-->
        <#--                                    ref="formA"-->
        <#--                                    @submit.prevent="submitA"-->
        <#--                            >-->
        <#--                                <v-file-input chips label="空白台账" v-model="originFileA"></v-file-input>-->
        <#--                                <v-file-input small-chips multiple label="分店" v-model="dataFilesA"></v-file-input>-->
        <#--                                <v-btn-->
        <#--                                        text-->
        <#--                                        type="submit"-->
        <#--                                >-->
        <#--                                    submit-->
        <#--                                </v-btn>-->
        <#--                            </v-form>-->
        <#--                        </v-card-text>-->
        <#--                    </v-card>-->
        <#--                </v-col>-->
        <#--                <v-col-->
        <#--                        xs="12"-->
        <#--                        sm="12"-->
        <#--                        md="6"-->
        <#--                        lg="4"-->
        <#--                >-->
        <#--                    <v-card-->
        <#--                            class="mx-auto my-12"-->
        <#--                    >-->
        <#--                        <v-img-->
        <#--                                height="250"-->
        <#--                                src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"-->
        <#--                        ></v-img>-->

        <#--                        <v-card-title>增项</v-card-title>-->

        <#--                        <v-card-text>-->
        <#--                            <v-form-->
        <#--                                    ref="formB"-->
        <#--                                    @submit.prevent="submitB"-->
        <#--                            >-->
        <#--                                <v-file-input chips label="增项台账" v-model="originFileB"></v-file-input>-->
        <#--                                <v-file-input small-chips multiple label="分店" v-model="dataFilesB"></v-file-input>-->
        <#--                                <v-btn-->
        <#--                                        text-->
        <#--                                        type="submit"-->
        <#--                                >-->
        <#--                                    submit-->
        <#--                                </v-btn>-->
        <#--                            </v-form>-->
        <#--                        </v-card-text>-->
        <#--                    </v-card>-->
        <#--                </v-col>-->
        <#--                <v-col-->
        <#--                        xs="12"-->
        <#--                        sm="12"-->
        <#--                        md="6"-->
        <#--                        lg="4"-->
        <#--                >-->
        <#--                    <v-card-->
        <#--                            class="mx-auto my-12"-->
        <#--                    >-->
        <#--                        <v-img-->
        <#--                                height="250"-->
        <#--                                src="https://cdn.vuetifyjs.com/images/cards/house.jpg"-->
        <#--                        ></v-img>-->

        <#--                        <v-card-title>改造维修</v-card-title>-->

        <#--                        <v-card-text>-->
        <#--                            <v-form-->
        <#--                                    ref="formC"-->
        <#--                                    @submit.prevent="submitC"-->
        <#--                            >-->
        <#--                                <v-file-input chips label="台账" v-model="originFileC"></v-file-input>-->
        <#--                                <v-file-input small-chips multiple label="分店" v-model="dataFilesC"></v-file-input>-->
        <#--                                <v-btn-->
        <#--                                        text-->
        <#--                                        type="submit"-->
        <#--                                >-->
        <#--                                    submit-->
        <#--                                </v-btn>-->
        <#--                            </v-form>-->
        <#--                        </v-card-text>-->
        <#--                    </v-card>-->
        <#--                </v-col>-->
        <#--            </v-row>-->
        <#--        </v-container>-->
    </v-app>
</div>

<script src="https://cdn.jsdelivr.net/npm/vue@2.x/dist/vue.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vuetify@2.x/dist/vuetify.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
<script>
    new Vue({
        el: '#app',
        vuetify: new Vuetify({
            theme: {
                dark: false,
            },
        }),
        data: {
            items: [
                {title: 'Dashboard', icon: 'mdi-view-dashboard'},
                {title: 'Photos', icon: 'mdi-image'},
                {title: 'About', icon: 'mdi-help-box'},
            ],
            right: null,
        },
        methods: {},
    })

</script>
</body>
</html>