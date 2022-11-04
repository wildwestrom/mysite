<script lang="ts">
	import '../app.postcss';
	import NavBar from '$lib/NavBar.svelte';
	import ExternalLink from '$lib/ExternalLink.svelte';
	import 'tailwindcss/src/css/preflight.css';
	import '$lib/global.css';
</script>

<div class="container">
	<NavBar />
	<main>
		<slot />
	</main>
</div>
<footer>
	<p>
		This website is <ExternalLink
			link="https://www.gnu.org/licenses/agpl-3.0.html"
			>free software</ExternalLink
		>. Get the source code
		<ExternalLink link="https://github.com/wildwestrom/mysite"
			>here</ExternalLink
		>.
	</p>
</footer>

<style>
	.container {
		min-height: 100vh;
	}

	main {
		margin-inline: auto;
		padding: 0.5rem;
		max-width: 65ch;
	}

	footer {
		padding: 1rem;
		text-align: center;
		position: relative;
		font-size: clamp(0.5rem, 3.5vw, 0.8rem);
	}
</style>
