<script lang="ts">
  import NavBar from '../components/NavBar.svelte'
  import ExternalLink from '../components/ExternalLink.svelte'
  import '../preflight.css'
  import '../global.css'
</script>

<div class="container">
  <NavBar />
  <main>
    <slot />
  </main>
</div>
<footer>
  <p>Copyright © 2022 Christian Westrom</p>
  <p>
    This website is <ExternalLink
      link="https://www.gnu.org/licenses/agpl-3.0.html"
      >free software</ExternalLink
    >. Get the source code <ExternalLink
      link="https://github.com/wildwestrom/mysite">here</ExternalLink
    >.
  </p>
</footer>

<style>
  .container {
    min-height: 100vh;
  }

  main {
    margin: auto;
    max-width: 65ch;
  }

  footer {
    padding: 1rem;
    text-align: center;
    position: relative;
    font-size: clamp(0.5rem, 3.5vw, 0.8rem);
  }
</style>
