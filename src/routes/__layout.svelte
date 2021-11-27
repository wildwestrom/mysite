<!-- src/routes/__layout.svlete -->
<script>
  import NavBar from '../components/NavBar.svelte';
  import '../app.css';
</script>

<NavBar />

<main>
  <slot />
</main>

<style>
  main {
    @apply m-4;
  }
</style>
