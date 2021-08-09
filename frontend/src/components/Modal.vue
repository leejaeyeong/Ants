<template>
  <transition name="modal" appear>
    <div class="modal modal__dim" @click.self="$emit('close')">
      <div class="modal-window">
        <div class="modal-content">
          <slot/>
        </div>
        <footer class="modal-footer">
          <slot name="footer">
            <button @click="$emit('close')">Close</button>
          </slot>
        </footer>
      </div>
    </div>
  </transition>
</template>

<style lang="scss">

.modal-enter {
  opacity: 0;
  .modal__content {
    transform: translateY(30px);
    transition: transform 0.6s;
  }
}
.modal-enter-active {
  transition: opacity 0.3s;
}
.modal-leave-active {
  transition: opacity 0.3s 0.05s;
}
.modal-leave-to {
  opacity: 0;
  .modal__content {
    transform: translateY(30px);
    transition: transform 0.6s;
  }
}
</style>
