<template>
  <div class="app-container">
    <div class="overview">
      <div class="stat-card" v-for="(value, key) in overviewData" :key="key">
        <div class="stat-title">{{ key }}</div>
        <div class="stat-value">{{ value }}</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      overviewData: {}
    };
  },
  created() {
    this.fetchOverviewData();
  },
  methods: {
    fetchOverviewData() {
      this.$axios.get("/dev-api/asset/statistics/overview")
        .then(response => {
          this.overviewData = response.data;
        })
        .catch(error => {
          console.error("Failed to fetch overview data:", error);
        });
    }
  }
};
</script>

<style scoped>
.app-container {
  padding: 20px;
}

.overview {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  gap: 20px;
}

.stat-card {
  background-color: #fff;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.stat-title {
  font-size: 18px;
  font-weight: bold;
  margin-bottom: 10px;
}

.stat-value {
  font-size: 24px;
  color: #42b983;
}
</style>