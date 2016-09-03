try{
if(top.naviframe1.indexEnter.location.href.indexOf('FavEnter')>-1)
  top.naviframe1.indexEnter.document.indexForm.indexLookup.value=document.title;
}catch(e){}finally{}


if(top.naviframe1.tocList != null && top.naviframe1.tocList.location.href.indexOf('tocFrame')>-1)
{
  try{
    top.naviframe1.tocList.loadSynchPage(myid);
  }catch(e){}finally{}
}

/*
 * This is the function that actually highlights a text string by
 * adding HTML tags before and after all occurrences of the search
 * term. You can pass your own tags if you'd like, or if the
 * highlightStartTag or highlightEndTag parameters are omitted or
 * are empty strings then the default <font> tags will be used.
 */
function doHighlight(bodyText, searchTerm) 
{
  // the highlightStartTag and highlightEndTag parameters are optional
 // highlightStartTag = "<font style='color:black; background-color:#C0D5D5;'>";
// highlightStartTag = "<font style='color:whiteblack; background-color:yellow;'>";
 highlightStartTag = "<font style='color:white; background-color:#8DB71F;'>";
  highlightEndTag = "</font>";
      
  // find all occurences of the search term in the given text,
  // and add some "highlight" tags to them (we're not using a
  // regular expression search, because we want to filter out
  // matches that occur within HTML tags and script blocks, so
  // we have to do a little extra validation)
  var newText = "";
  var i = -1;
  var lcSearchTerm = searchTerm.toLowerCase();
  var lcBodyText = bodyText.toLowerCase();
    
  while (bodyText.length > 0) {
    i = lcBodyText.indexOf(lcSearchTerm, i+1);
    if (i < 0) {
      newText += bodyText;
      bodyText = "";
    } else {
      // skip anything inside an HTML tag
      if (bodyText.lastIndexOf(">", i) >= bodyText.lastIndexOf("<", i)) {
        // skip anything inside a <script> block
        if (lcBodyText.lastIndexOf("/script>", i) >= lcBodyText.lastIndexOf("<script", i)) {
          newText += bodyText.substring(0, i) + highlightStartTag + bodyText.substr(i, searchTerm.length) + highlightEndTag;
          bodyText = bodyText.substr(i + searchTerm.length);
          lcBodyText = bodyText.toLowerCase();
          i = -1;
        }
      }
    }
  }  
  return newText;
}

function tableRows() {
 if (document.getElementsByTagName) {
 // var tables = document.getElementsByTagName('table');
  var tablesA = document.getElementsByClassName('A');
  for (var i = 0; i < tablesA.length; i++) {
   var trs = tablesA[i].getElementsByTagName('tr');
   var tds = tablesA[i].getElementsByTagName('td');
   
  // alert("r:"+trs.length);
  // alert("c:"+tds.length);
   var loop = tds.length/trs.length;
  // alert(loop);
      tds[0].className='firstHeaderA';
	for(var m=1; m<loop;m++){  
   	  tds[m].className='headerStyleA';
      }
	for (var j = 1; j < trs.length; j++) {
    if((j == (trs.length-1))){
   
		trs[j].className = 'lastRowOdd';
	}
	if(j==1)
	{	    
		trs[j].className = 'firstRow';
	}
    else
	{
     trs[j].className = 'odd';
	 }	
   }   
  }
  
  var tablesB = document.getElementsByClassName('B');
  for (var i = 0; i < tablesB.length; i++) {
   var trs = tablesB[i].getElementsByTagName('tr');
   var tds = tablesB[i].getElementsByTagName('td');
     var loop = tds.length/trs.length;
   //alert(loop);
      tds[0].className='firstHeaderA';
	for(var m=1; m<loop;m++){  
   	  tds[m].className='headerStyleA';
      }
      
	for (var j = 1; j < trs.length; j++) {
    if((j == (trs.length-1))){
   
		trs[j].className = 'lastRowOdd';
	}
	if(j==1)
	{	    
		trs[j].className = 'firstRow';
	}
    else
	{
     trs[j].className = 'odd';
	 }	
   }   
  }
  
   var tablesE = document.getElementsByClassName('E');
  for (var i = 0; i < tablesE.length; i++) {
   var trs = tablesE[i].getElementsByTagName('tr');
   var tds = tablesE[i].getElementsByTagName('td');
      var loop = tds.length/trs.length;
   //alert(loop);
      tds[0].className='firstHeaderA';
	for(var m=1; m<loop;m++){  
   	  tds[m].className='headerStyleA';
      }
      
	for (var j = 1; j < trs.length; j++) {
    if((j == (trs.length-1))){
   
		trs[j].className = 'lastRowOdd';
	}
	if(j==1)
	{	    
		trs[j].className = 'firstRow';
	}
    else
	{
     trs[j].className = 'odd';
	 }	
   }   
  }
  
   var tablesD = document.getElementsByClassName('D');
  for (var i = 0; i < tablesD.length; i++) {
   var trs = tablesD[i].getElementsByTagName('tr');
   var tds = tablesD[i].getElementsByTagName('td');
      var loop = tds.length/trs.length;
  // alert(loop);
      tds[0].className='firstHeaderA';
	for(var m=1; m<loop;m++){  
   	  tds[m].className='headerStyleA';
      }
      
	for (var j = 1; j < trs.length; j++) {
    if((j == (trs.length-1))){
   
		trs[j].className = 'lastRowOdd';
	}
	if(j==1)
	{	    
		trs[j].className = 'firstRow';
	}
    else
	{
     trs[j].className = 'odd';
	 }	
   }   
  }

 var tablesC = document.getElementsByClassName('C');
  for (var i = 0; i < tablesC.length; i++) {
   var trs = tablesC[i].getElementsByTagName('tr');
   var tds = tablesC[i].getElementsByTagName('td');
      var loop = tds.length/trs.length;
   //alert(loop);
      trs[0].className = 'noHeaderFirstRow';
      
	for (var j = 1; j < trs.length; j++) {
    if((j == (trs.length-1))){
   
		trs[j].className = 'lastRowOdd';
	}	
    else
	{
     trs[j].className = 'odd';
	}
  
  }
  }

  }
 }


/*
 * This is sort of a wrapper function to the doHighlight function.
 * It takes the searchText that you pass, optionally splits it into
 * separate words, and transforms the text on the current web page.
 * Only the "searchText" parameter is required; all other parameters
 * are optional and can be omitted.
 */
function highlightSearchTerms()
{
//tableRows(); 
  var searchText='';
  if(location.search != '')
    searchText=location.search.substring(1);
  else
    return;
var st = decodeURIComponent(searchText);
  searchText=st;
//  searchText=unescape(searchText);

  //we will split the
  // search string so that each word is searched for and highlighted
  // individually
  searchArray = searchText.split(" ");
  
  var bodyText = document.body.innerHTML;
  for (var i = 0; i < searchArray.length; i++) {
    bodyText = doHighlight(bodyText, searchArray[i]);
  }
  document.body.innerHTML = bodyText;

  return true;
}   



