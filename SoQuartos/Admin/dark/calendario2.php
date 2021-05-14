<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />
<link href='fullcalendar/packages/core/main.css' rel='stylesheet' />
<link href='fullcalendar/packages/daygrid/main.css' rel='stylesheet' />
<link href='fullcalendar/packages/timegrid/main.css' rel='stylesheet' />
<link href='fullcalendar/packages/list/main.css' rel='stylesheet' />
<script src='fullcalendar/packages/core/main.js'></script>
<script src='fullcalendar/packages/interaction/main.js'></script>
<script src='fullcalendar/packages/daygrid/main.js'></script>
<script src='fullcalendar/packages/timegrid/main.js'></script>
<script src='fullcalendar/packages/list/main.js'></script>
<script src='fullcalendar/packages/core/locales/pt.js'></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>

<?php $idquarto = $_GET['idq'] ?>

var idquarto = <?php echo $idquarto ?>

  document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
      height: 370,
      fixedWeekCount: false,
      plugins: [ 'interaction', 'dayGrid', 'timeGrid', 'list'],
      header: {
        left: 'prev,next today',
        center: 'title',
        right: 'dayGridMonth,timeGridWeek,timeGridDay,listMonth'
      },
      locale: 'pt',
      /*defaultDate: '2019-08-12',*/
      navLinks: true, // can click day/week names to navigate views
      selectable: true,
      selectMirror: true,
      editable: true,
      durationeditable : true,
      eventLimit: true, // allow "more" link when too many events
      events: 'calendario.php?idq='+idquarto,

      
    });



    calendar.render();
  });

</script>
<style>

  body {
    
    padding: 0;
    font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
    font-size: 14px;
  }

  #calendar {
    max-width: 500px;
    height: 100px;
    margin: 0 auto;
  }

</style>
</head>
<body>

  <div id='calendar'></div>

</body>
</html>
