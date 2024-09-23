<template>

    <v-data-table
        :headers="headers"
        :items="kiosk"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'KioskView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
            ],
            kiosk : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/kiosks'))

            temp.data._embedded.kiosks.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.kiosk = temp.data._embedded.kiosks;
        },
        methods: {
        }
    }
</script>

