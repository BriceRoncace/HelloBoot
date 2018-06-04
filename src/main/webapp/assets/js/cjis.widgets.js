/* global cjis */

(function($, cjis) {
  cjis.widgets = (function() {
    function initWidgetModalEvents() {
      $("#addWidget").click(function() {
        showWidgetModal({id: "", name: "", expiration: ""});
      });
      
      $("[data-id]").click(function() {
        var $this = $(this);
        showWidgetModal({id: $this.data("id"), name: $this.data("name"), expiration: $this.data("expiration")});
      });
      
      function showWidgetModal(widget) {
        $("#id").val(widget.id);
        $("#name").val(widget.name);
        $("#expiration").val(widget.expiration);
        $("#saveWidgetModal").modal();
      }
    }
    
    function initAll() {
      initWidgetModalEvents();
    }
    
    return {
      init: initAll
    };
  })();
})(jQuery, cjis);