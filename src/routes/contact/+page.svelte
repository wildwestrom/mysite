<script lang="ts">
	import ContactLink from './ContactLink.svelte';
</script>

<svelte:head>
	<title>Westrom.xyz - Contact</title>
</svelte:head>

<h1>Find me</h1>

<ul>
	<ContactLink href="https://github.com/wildwestrom" name="Github" icon="github" />
	<ContactLink href="https://matrix.to/#/@wildwestrom:nitro.chat" name="Matrix" icon="matrix" />
	<ContactLink href="mailto:c.westrom@westrom.xyz" name="Email" icon="envelope" />
	<ContactLink href="https://discordapp.com/users/153415020556845056" name="Discord" icon="discord" />
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
</style>
