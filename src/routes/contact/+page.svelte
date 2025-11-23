<script lang="ts">
	import ContactLink from './ContactLink.svelte';
</script>

<svelte:head>
	<title>Westrom.xyz - Contact</title>
</svelte:head>

<h1>Find me</h1>

<ul>
	<ContactLink href="https://github.com/wildwestrom" name="Github">
		<svg inline-src="github" />
	</ContactLink>
	<ContactLink href="https://hackers.pub/@wildwestrom" name="Fediverse">
		<svg inline-src="hackerspub" />
	</ContactLink>
	<ContactLink href="https://matrix.to/#/@wildwestrom:nitro.chat" name="Matrix">
		<svg inline-src="matrix" />
	</ContactLink>
	<ContactLink href="mailto:c.westrom@westrom.xyz" name="Email">
		<svg inline-src="envelope" />
	</ContactLink>
	<ContactLink href="https://discordapp.com/users/153415020556845056" name="Discord">
		<svg inline-src="discord" />
	</ContactLink>
</ul>

<style>
	ul {
		display: flex;
		flex-direction: column;
		gap: 0.25em 1em;
		align-items: center;
		justify-content: space-evenly;
		font-size: larger;
	}

	svg {
		width: 4rem;
		height: 4rem;
		margin: 0 1rem;
	}

	/* The Hackers.pub icon is special. It needs an outline so the body always shows up dark. I also wanted it to match whatever text color or background color is set. */
	:global(.hackerspub-outline) {
		fill: white;
		@media (prefers-color-scheme: light) {
			fill: #00000000; /* transparent */
		}
		@media (prefers-color-scheme: dark) {
			fill: var(--text-color);
		}
	}
	:global(.hackerspub-white-parts) {
		fill: var(--text-color);
		@media (prefers-color-scheme: light) {
			fill: var(--bg-primary);
		}
		@media (prefers-color-scheme: dark) {
			fill: var(--text-color);
		}
	}

	@media (prefers-color-scheme: dark) {
		:global(svg:hover .hackerspub-outline, svg:hover .hackerspub-white-parts) {
			fill: var(--hover-color);
		}
	}
	@media (prefers-color-scheme: light) {
		:global(svg:hover .hackerspub-black-parts) {
			fill: var(--hover-color);
		}
	}

	:global(.hackerspub-black-parts) {
		fill: var(--text-color);
		@media (prefers-color-scheme: light) {
			fill: var(--text-color);
		}
		@media (prefers-color-scheme: dark) {
			fill: var(--bg-primary);
		}
	}
</style>
