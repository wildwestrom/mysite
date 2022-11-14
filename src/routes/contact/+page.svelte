<script lang="ts">
	import TodoItem from '$lib/TodoItem.svelte'
</script>

<svelte:head>
	<title>Westrom.xyz - Contact</title>
</svelte:head>

<h1>Contact page</h1>

<TodoItem href="https://github.com/wildwestrom/mysite/issues/21">
		Add some actual links to the few social media sites I have.
</TodoItem>

<ul>
	<li>Github</li>
	<li>Matrix</li>
	<li>Discord</li>
	<li>Email</li>
</ul>

<style lang="postcss">
	ul {
		li {
			margin-left: 6rem;
		}
		li::marker {
			content: image('/discord_default.svg');
		}
	}
</style>
