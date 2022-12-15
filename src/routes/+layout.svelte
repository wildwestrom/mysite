<script lang="ts">
	import '../app.postcss';
	import NavBar from './NavBar.svelte';
	import ExternalLink from '$lib/ExternalLink.svelte';
</script>

<div>
	<NavBar />
	<main>
		<slot />
	</main>
</div>
<footer>
	<p>
		This website is <ExternalLink href="https://www.gnu.org/licenses/agpl-3.0.html"
			>free software</ExternalLink
		>. Get the source code <ExternalLink href="https://github.com/wildwestrom/mysite"
			>here</ExternalLink
		>.
	</p>
</footer>

<style>
	div {
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
