<template>
  <div id="globalHeader">
    <a-row :wrap="false">
      <a-col>
        <RouterLink to="/">
          <div class="title-bar">
            <div class="title">阿迪达斯电商数据分析平台</div>
          </div>
        </RouterLink>
      </a-col>
    </a-row>
  </div>
</template>

<script lang="ts" setup>
</script>

<style scoped>
.title-bar {
  display: flex;
  align-items: center;
}

.title {
  color: black;
  font-size: 18px;
  margin-left: 16px;
}
</style>
