$(document).ready(function () {
  $(".progressYT").hide();
  $(".progressVM").hide();
  $(".FYI").hide();


  $(".YouTube").submit(function (event) {
    event.preventDefault();
    var song = $(".YT").val();
    $.ajax({
      url: 'https://www.googleapis.com/youtube/v3/search?part=snippet&type=video&q='+song+'&maxResults=8&key=AIzaSyBKQqHhVXFX3K42hS5Yoz15S-HmuVGmNV4',
      method: 'GET',
      success: function (response) {
        $(".results").empty();
        for (var i = 0; i < response.items.length; i++) {
          console.log(response.items[i]);
          $(".results").append(
            "<h3>"+response.items[i].snippet.title+"</h3>"
           +'<iframe width="160" height="120" src="https://www.youtube.com/embed/'+response.items[i].id.videoId+'"></iframe>'+'<form action="/playlists/new" method="post">'+'<label for="typeName">Select Genre of this song </label>'
           +'<select id="typeName" name="typeName" type="text">'
           +'<option value="General">General</option>'
           +'<option value="Hip Hop">Hip Hop</option>'
           +'<option value="House">House</option>'
           +'<option value="Rock">Rock</option>'
           +'<option value="Pop">Pop</option>'
           +'<option value="Gospel">Gospel</option>'
           +'<option value="Country">Country</option>'
           +'<option value="Other">Other</option>'
           +'</select>'
           +'<input id="trackName" name="trackName" type="hidden" value="'+response.items[i].snippet.title+'">'
           +'<input id="thumbNail" name="thumbNail" type="hidden" value="'+response.items[i].snippet.thumbnails.default.url+'">'
           +'<input id="trackLink" name="trackLink" type="hidden" value="'+response.items[i].id.videoId+'">'
           +'<input id="host" name="host" type="hidden" value="YouTube">'
           +'<button type="submit" class="btn btn-success" name="button">'
           +'<i class="glyphicon glyphicon-plus"></i>'
           +'</button>'
           +'</form>'

        );

        }
      },

      beforeSend: function () {
        $(".progressYT").show();
        $(".searchYT").hide();
        $(".FYI").show();
        console.log("Loading");
      }

    }).always(function () {
      $(".progressYT").hide();
      $(".searchYT").show();
      console.log("finished");

    });

  });

  $(".Vimeo").submit(function (event) {
    event.preventDefault();
    var song = $(".VM").val();
    $.ajax({
      url: 'https://api.vimeo.com/videos?per_page=8&query='+song+'&access_token=81c0ca8caf411a2b56b5707d8c475d0e',
      method: 'GET',
      success:  function (response) {
        $(".results").empty();

        // console.log(response.data);

        for (var i = 0; i < response.data.length; i++) {
          var videoLink = response.data[i].uri;
          var newVideoLink = videoLink.replace(/s/i,'');
          // console.log(response.data[i]);
          var time = response.data[i].duration;
          var seconds = time%60;
          var minutes = Math.floor(time/60);
          var duration = minutes + ":"+(seconds<10?'0'+seconds:seconds);

          $(".results").append(
            "<h3>"+"Track name:  "+response.data[i].name+"</h3>"+"<h4> Track Duration: "+duration+"</h4>"
          +'<iframe src="https://player.vimeo.com'+newVideoLink+'?title=0&byline=0&portrait=0&badge=0&autopause=0&player_id=0" width="160" height="120" frameborder="0" title="'+response.data[i].name+'" webkitallowfullscreen mozallowfullscreen allowfullscreen></iframe>'
          +'<form action="/playlists/new" method="post">'+'<label for="typeName">Select Genre of this song </label>'
          +'<select id="typeName" name="typeName" type="text">'
          +'<option value="General">General</option>'
          +'<option value="Hip Hop">Hip Hop</option>'
          +'<option value="House">House</option>'
          +'<option value="Rock">Rock</option>'
          +'<option value="Pop">Pop</option>'
          +'<option value="Gospel">Gospel</option>'
          +'<option value="Country">Country</option>'
          +'<option value="Other">Other</option>'

          +'</select>'

          +'<input id="trackName" name="trackName" type="hidden" value="'+response.data[i].name+'">'

          +'<input id="thumbNail" name="thumbNail" type="hidden" value="'+response.data[i].pictures.sizes[0].link+'">'

          +'<input id="trackLink" name="trackLink" type="hidden" value="'+newVideoLink+'">'

          +'<input id="host" name="host" type="hidden" value="Vimeo">'

          +'<button type="submit" class="btn btn-success" name="button">'

          +'<i class="glyphicon glyphicon-plus"></i>'

          +'</button>'

          +'</form>'

        );

        }

      },

      beforeSend: function () {

        $(".progressVM").show();

        $(".searchVM").hide();

        $(".FYI").hide();

        console.log("sending");

      }

    }).always(function () {

      $(".progressVM").hide();

      $(".searchVM").show();

      console.log("Done!");

    });

  });

});
