<template>
  <section class="hero is-success is-fullheight">
    <div class="hero-body">
      <div class="container">
        <h1>막대 그래프</h1>
        <ul>
          <bar-chart></bar-chart>
        </ul>
      </div>
    </div>
  </section>
</template>

<script>
import BarChart from "@/components/Charts/BarChart3.vue";
export default {
  components: { BarChart },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style></style>
