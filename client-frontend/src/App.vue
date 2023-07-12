<template>
  <div id="app">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <div class="container-fluid">
        <router-link to="/" class="navbar-brand">Client Manager</router-link>
        <div class="collapse navbar-collapse">
          <div class="navbar-nav">
            <router-link to="/client" class="nav-link">Client List</router-link>
            <router-link to="/add" class="nav-link">Add New Client</router-link>
          </div>
        </div>
      </div>
    </nav>
    <div class="container">
      <router-view />
    </div>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>
