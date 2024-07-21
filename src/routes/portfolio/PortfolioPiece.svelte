<script lang="ts">
	export let name: string;
</script>

<li>
	<h2>{name}</h2>
	<slot />
</li>

<style>
	li {
		margin-bottom: 0.75em;
	}
</style>
