<template>
    <div>
        <div v-if="!submitted">
            <div class="mb-3">
                <label for="firstName" class="form-label">First Name</label>
                <input type="text" class="form-control" id="firstName" required name="firstName" v-model="client.firstName">
            </div>
            <div class="mb-3">
                <label for="lastName" class="form-label">Last Name</label>
                <input type="text" class="form-control" id="lastName" required name="lastName" v-model="client.lastName">
            </div>
            <div class="mb-3">
            <label for="middleName" class="form-label">Middle Name</label>
            <input type="text" class="form-control" id="middleName" required name="middleName" v-model="client.middleName">
            </div>
            <div class="mb-3">
            <label for="middleName" class="form-label">Address</label>
            <input type="text" class="form-control" id="address" required name="address" v-model="client.address">
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" id="email" required name="email" v-model="client.email">
            </div>
            <div class="mb-3">
                <label for="phone" class="form-label">Phone</label>
                <input type="text" class="form-control" id="phone" required name="phone" v-model="client.phone">
            </div>
            <div class="mb-3">
                <button @click="saveClient" class="btn btn-primary">Submit</button>
            </div>
        </div>
        <div v-else>
            <div class="alert alert-success" role="alert">
                Save client successfully!
            </div>
            <button @click="newClient" class="btn btn-primary">Add New Client</button>
        </div>
    </div>
</template>

<script>
import ClientDataService from '../services/ClientDataService'

export default {
    name: 'add-client',
    data() {
        return {
            client: {
                id: null,
                firstName: "",
                lastName: "",
                middleName: "",
                address: "",
                email: "",
                phone: ""
            },
            submitted: false
        }
    },
    methods: {
        saveClient() {
            var data = {
                firstName: this.client.firstName,
                lastName: this.client.lastName,
                middleName: this.client.middleName,
                address: this.client.address,
                email: this.client.email,
                phone: this.client.phone
            }
            ClientDataService.create(data)
                .then(response => {
                    this.client.id = response.data.id
                    this.submitted = true;
                })
                .catch( e => {
                    alert(e)
                })
        },
        newClient() {
            this.submitted = false
            this.client = {}
        }
    }
}
</script>
