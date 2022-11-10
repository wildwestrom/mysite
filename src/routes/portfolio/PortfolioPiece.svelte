<script lang="ts">
	export let name: string;
</script>

<li>
	<h3>{name}</h3>
	<slot />
</li>

<style>
	li {
		margin-bottom: .75em;
	}
</style>
