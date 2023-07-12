<template>
    <div v-if="currentClient">
        <div class="mb-3">
                <label for="firstName" class="form-label">First Name</label>
                <input type="text" class="form-control" id="firstName" required name="firstName" v-model="currentClient.firstName">
            </div>
            <div class="mb-3">
                <label for="lastName" class="form-label">Last Name</label>
                <input type="text" class="form-control" id="lastName" required name="lastName" v-model="currentClient.lastName">
            </div>
            <div class="mb-3">
                <label for="middleName" class="form-label">Last Name</label>
                <input type="text" class="form-control" id="middleName" required name="middleName" v-model="currentClient.middleName">
            </div>
            <div class="mb-3">
                <label for="address" class="form-label">Last Name</label>
                <input type="text" class="form-control" id="address" required name="address" v-model="currentClient.address">
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" id="email" required name="email" v-model="currentClient.email">
            </div>
            <div class="mb-3">
                <label for="phone" class="form-label">Phone</label>
                <input type="text" class="form-control" id="phone" required name="phone" v-model="currentClient.phone">
            </div>
            <div class="mb-3">
                <button @click="updateClient" class="btn btn-primary me-3">Update</button>
                <button @click="deleteClient" class="btn btn-danger">Delete</button>
            </div>
             <div class="alert alert-success" role="alert" v-if="message">
                {{message}}
            </div>
    </div>
</template>

<script>
import ClientDataService from '../services/ClientDataService'

export default {
    name: 'edit-client',
    data() {
        return {
            currentClient: null,
            message: ''
        }
    },
    methods: {
        getClient(id) {
            ClientDataService.get(id)
                .then(response => {
                    this.currentClient = response.data
                })
                .catch(e => {
                    alert(e)
                })
        },
        updateClient() {
            ClientDataService.update(this.currentClient.id, this.currentClient)
                .then(() => {
                    this.message = 'The client was updated successfully!'
                })
                .catch(e => {
                    alert(e)
                })
        },
        deleteClient() {
            ClientDataService.delete(this.currentClient.id)
                .then(() => {
                    this.$router.push({name: 'clients'})
                })
                .catch(e => {
                    alert(e)
                })
        }
    },
    mounted() {
        this.getClient(this.$route.params.id)
    }
}
</script>
