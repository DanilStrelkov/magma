<template>
    <div>
        <table class="table">
            <thead>
                <tr>
                <th scope="col">First Name</th>
                <th scope="col">Last Name</th>
                <th scope="col">Middle Name</th>
                <th scope="col">Address</th>
                <th scope="col">Email</th>
                <th scope="col">Phone</th>
                <th scope="col">Actions</th>
                </tr>
            </thead>
            <tbody v-for="(client, index) in clients" :key="index">
                <tr>
                    <td>{{client.firstName}}</td>
                    <td>{{client.lastName}}</td>
                    <td>{{client.middleName}}</td>
                    <td>{{client.address}}</td>
                    <td>{{client.email}}</td>
                    <td>{{client.phone}}</td>
                    <td><a :href="'/api/client/' + client.id" class="btn btn-primary">Edit</a></td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
import ClientDataService from '../services/ClientDataService'

export default {
    name: 'clientsStat',
    data() {
        return {
            clients: []
        }
    },
    methods: {
        retrieveClients() {
            ClientDataService.getAll()
                .then(response => {
                    this.clients = response.data
                })
                .catch(e => {
                    alert(e)
                })
        }
    },
    mounted() {
        this.retrieveClients()
    }
}
</script>
