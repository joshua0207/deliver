
//==================== Get Value ==========================
/*
function GetValue() {
  if (document.forms["appl"].GetValueFlag.checked) {
    document.forms["appl"].receipt_name.value = document.forms["appl"].name.value;
    }
}
  */
var AryCountyLat = new Array(25.047658,25.129239,25.06939,24.677301,24.798812,
		24.781163,24.961744,24.421616,24.178509,24.076304,
		23.905593,23.479976,23.463345,23.713009,23.075567,
		22.624628,23.566505,22.682925,22.758018,23.992663,
		24.432694,26.150238);
var AryCountyLng = new Array(121.520205,121.738322,121.49703,121.767418,120.974944,
		121.019232,121.223252,120.773681,120.645161,120.539761,
		120.688462,120.449145,120.241327,120.437107,120.293448,
		120.305958,119.568114,120.499313,121.14491,121.602645,
		118.318741,119.929934);
 var AryAreaCodeText = new Array("(02)台北,基隆", "(03)桃園,新竹,宜蘭,花蓮", 
    "(037)苗栗", "(04)台中、彰化", "(049)南投", "(05)雲林,嘉義", "(06)台南,澎湖", "(07)高雄",
    "(08)屏東", "(089)台東", "(0836)馬祖", "(082)金門,烏坵,東沙/南沙");
	
 var AryAreaCode = new Array("02", "03","037","04","049","05","06", "07",
    "08", "089", "0836", "082");	
 
//==================== for zip code begin =========================
var AryCounty = new Array("台北市", "基隆市", "新北市", "宜蘭縣", "新竹市", 
    "新竹縣", "桃園縣", "苗栗縣", "台中市", "彰化縣",
    "南投縣", "嘉義市", "嘉義縣", "雲林縣", "台南市",
    "高雄市", "澎湖縣", "屏東縣", "台東縣", "花蓮縣",
    "金門縣", "連江縣");
 
var AryZone = new Array(22);

// for "台北市"
AryZone[0] = new Array("中正區","大同區","中山區","松山區","大安區",
  "萬華區","信義區","士林區","北投區","內湖區","南港區",
  "文山區");
// for "基隆市"
AryZone[1] = new Array("仁愛區","信義區","中正區","中山區","安樂區",
  "暖暖區","七堵區");
// for "新北市"
AryZone[2] = new Array("萬里區","金山區","板橋區","汐止區","深坑區","石碇區","瑞芳區",
  "平溪區","雙溪區","貢寮區","新店區","坪林區","烏來區","永和區","中和區","土城區",
  "三峽區","樹林區","鶯歌區","三重區","新莊區","泰山區","林口區","蘆洲區","五股區",
  "八里區","淡水區","三芝區","石門區");
// for "宜蘭縣"
AryZone[3] = new Array("宜蘭市","頭城鎮","礁溪鄉","壯圍鄉","員山鄉","羅東鎮","三星鄉",
  "大同鄉","五結鄉","冬山鄉","蘇澳鎮","南澳鄉","釣魚台");
// for "新竹市"
AryZone[4] = new Array("東區","北區","香山區");
// for "新竹縣"
AryZone[5] = new Array("竹北市","湖口鄉","新豐鄉","新埔鎮","關西鎮","芎林鄉","寶山鄉",
  "竹東鎮","五峰鄉","橫山鄉","尖石鄉","北埔鄉","峨眉鄉");
// for "桃園縣"
AryZone[6] = new Array("中壢市","平鎮市","龍潭鄉","楊梅市","新屋鄉","觀音鄉","桃園市",
  "龜山鄉","八德市","大溪鎮","復興鄉","大園鄉","蘆竹鄉");
// for "苗栗縣"
AryZone[7] = new Array("竹南鎮","頭份鎮","三灣鄉","南庄鄉","獅潭鄉","後龍鎮","通霄鎮",
  "苑裡鎮","苗栗市","造橋鄉","頭屋鄉","公館鄉","大湖鄉","泰安鄉","銅鑼鄉","三義鄉",
  "西湖鄉","卓蘭鎮");
// for "台中市"
AryZone[8] = new Array("中區","東區","南區","西區","北區","北屯區",
  "西屯區","南屯區","太平區","大里區","霧峰區","烏日區","豐原區","后里區","石岡區",
  "東勢區","和平區","新社區","潭子區","大雅區","神岡區","大肚區","沙鹿區","龍井區",
  "梧棲區","清水區","大甲區","外埔區","大安區");

// for "彰化縣"
AryZone[9] = new Array("彰化市","芬園鄉","花壇鄉","秀水鄉","鹿港鎮","福興鄉","線西鄉",
  "和美鎮","伸港鄉","員林鎮","社頭鄉","永靖鄉","埔心鄉","溪湖鎮","大村鄉","埔鹽鄉",
  "田中鎮","北斗鎮","田尾鄉","埤頭鄉","溪州鄉","竹塘鄉","二林鎮","大城鄉","芳苑鄉",
  "二水鄉");
// for "南投縣"
AryZone[10] = new Array("南投市","中寮鄉","草屯鎮","國姓鄉","埔里鎮","仁愛鄉","名間鄉",
  "集集鎮","水里鄉","魚池鄉","信義鄉","竹山鎮","鹿谷鄉");
// for "嘉義市"
AryZone[11] = new Array("東區","西區");
// for "嘉義縣"
AryZone[12] = new Array("番路鄉","梅山鄉","竹崎鄉","阿里山鄉","中埔鄉","大埔鄉",
"水上鄉","鹿草鄉","太保市","朴子市","東石鄉","六腳鄉","新港鄉","民雄鄉","大林鎮","溪口鄉",
"義竹鄉","布袋鎮");
// for "雲林縣"
AryZone[13] = new Array("斗南鎮","大埤鄉","虎尾鎮","土庫鎮","褒忠鄉","東勢鄉","台西鄉",
  "崙背鄉","麥寮鄉","斗六市","林內鄉","古坑鄉","莿桐鄉","西螺鎮","二崙鄉","北港鎮",
  "水林鄉","口湖鄉","四湖鄉","元長鄉");
// for "台南市"
AryZone[14] = new Array("中西區","東區","南區","北區","安平區",
  "安南區","永康區","歸仁區","新化區","左鎮區","玉井區","楠西區","南化區",
  "仁德區","關廟區","龍崎區","官田區","麻豆區","佳里區","西港區","七股區","將軍區",
  "學甲區","北門區","新營區","後壁區","白河區","東山區","六甲區","下營區","柳營區",
  "鹽水區","善化區","大內區","山上區","新市區","安定區");

// for "高雄市"
AryZone[15] = new Array("新興區","前金區","苓雅區","鹽埕區","鼓山區",
  "旗津區","前鎮區","三民區","楠梓區","小港區","左營區","仁武區","大社區","東沙群島",
  "南沙群島","岡山區","路竹區","阿蓮區","田寮區","燕巢區",
  "橋頭區","梓官區","彌陀區","永安區","湖內區","鳳山區","大寮區","林園區","鳥松區",
  "大樹區","旗山區","美濃區","六龜區","內門區","杉林區","甲仙區","桃源區","那瑪夏區",
  "茂林區","茄萣區");

// for "澎湖縣"
AryZone[16] = new Array("馬公市","西嶼鄉","望安鄉","七美鄉","白沙鄉","湖西鄉");
// for "屏東縣"
AryZone[17] = new Array("屏東市","三地門鄉","霧台鄉","瑪家鄉","九如鄉","里港鄉","高樹鄉",
  "鹽埔鄉","長治鄉","麟洛鄉","竹田鄉","內埔鄉","萬丹鄉","潮州鎮","泰武鄉","來義鄉",
  "萬巒鄉","崁頂鄉","新埤鄉","南州鄉","林邊鄉","東港鎮","琉球鄉","佳冬鄉","新園鄉",
  "枋寮鄉", "枋山鄉","春日鄉","獅子鄉","車城鄉","牡丹鄉","恆春鎮","滿州鄉");
// for "台東縣"
AryZone[18] = new Array("台東市","綠島鄉","蘭嶼鄉","延平鄉","卑南鄉","鹿野鄉","關山鎮",
  "海端鄉","池上鄉","東河鄉","成功鎮","長濱鄉","太麻里鄉","金峰鄉","大武鄉","達仁鄉");
// for "花蓮縣"
AryZone[19] = new Array("花蓮市","新城鄉","秀林鄉","吉安鄉","壽豐鄉","鳳林鎮","光復鄉",
  "豐濱鄉","瑞穗鄉","萬榮鄉","玉里鎮","卓溪鄉","富里鄉");
// for "金門縣"
AryZone[20] = new Array("金沙鎮","金湖鎮","金寧鄉","金城鎮","烈嶼鄉","烏坵鄉");
// for "連江縣"
AryZone[21] = new Array("南竿鄉","北竿鄉","莒光鄉","東引鄉");


 
/* 
var AryZipCode = new Array(27);
// for "台北市"
AryZipCode[0] = new Array("100","103","104","105","106","108","110","111",
  "112","114","115","116","117");
// for "基隆市"
AryZipCode[1] = new Array("200","201","202","203","204","205","206");
// for "台北縣"
AryZipCode[2] = new Array("207","208","220","221","222","223","224","226",
  "227","228","231","232","233","234","235","236","237","238","239",
  "241","242","243","244","247","248","249","251","252","253");
// for "宜蘭縣"
AryZipCode[3] = new Array("260","261","262","263","264","265","266","267",
  "268","269","270","272");
// for "新竹市"
AryZipCode[4] = new Array("300","300","300");
// for "新竹縣"
AryZipCode[5] = new Array("302","303","304","305","306","307","308","310",
  "311","312","313","314","315");
// for "桃園縣"
AryZipCode[6] = new Array("320","324","325","326","327","328","330","333",
  "334","335","336","337","338");
// for "苗栗縣"
AryZipCode[7] = new Array("350","351","352","353","354","356","357",
  "358","360","361","362","363","364","365","366","367","368","369");
// for "台中市"
AryZipCode[8] = new Array("400","401","402","403","404","406","407","408");
// for "台中縣"
AryZipCode[9] = new Array("411","412","413","414","420","421","422","423",
  "424","426","427","428","429","432","433","434","435","436","437",
  "438","439");
// for "彰化縣"
AryZipCode[10] = new Array("500","502","503","504","505","506","507","508",
  "509","510","511","5112","513","514","515","516","520","521","522",
  "523","524","525","526","527","528","530");
// for "南投縣"
AryZipCode[11] = new Array("540","541","542","544","545","546","551","552",
  "553","555","556","557","558");
// for "嘉義市"
AryZipCode[12] = new Array("600","600");
// for "嘉義縣"
AryZipCode[13] = new Array("602","603","604","605","606","607","608","611",
  "612","613","614","615","616","621","622","623","624","625");
// for "雲林縣"
AryZipCode[14] = new Array("630","631","632","633","634","635","636","637",
  "638","640","643","646","647","648","649","651","652","653","654",
  "655");
// for "台南市"
AryZipCode[15] = new Array("700","701","702","703","704","708","709");
// for "台南縣"
AryZipCode[16] = new Array("710","711","712","713","714","715","716","717",
  "718","719","720","721","722","723","724","725","726","727","730",
  "731","732","733","734","735","736","737","741","742","743","744",
  "745");
// for "高雄市"
AryZipCode[17] = new Array("800","801","802","803","804","805","806","807",
  "811","812","813");
// for "高雄縣"
AryZipCode[18] = new Array("814","815","820","821","822","823","824","825",
  "826","827","828","829","830","831","832","833","840","842","843",
  "844","845","846","847","848","849","851","852");
// for "澎湖縣"
AryZipCode[19] = new Array("880","881","882","883","884","885");
// for "屏東縣"
AryZipCode[20] = new Array("900","901","902","903","904","905","906","907",
  "908","909","911","912","913","920","921","922","923","924","925",
  "926","927","928","929","931","932","940","941","942","943","944",
  "945","946","947");
// for "台東縣"
AryZipCode[21] = new Array("950","951","952","953","954","955","956","957",
  "958","959","961","962","963","964","965","966");
// for "花蓮縣"
AryZipCode[22] = new Array("970","971","972","973","974","975","976","977",
  "978","979","981","982","983");
// for "金門縣"
AryZipCode[23] = new Array("890","891","892","893","894","896");
// for "連江縣"
AryZipCode[24] = new Array("209","210","211","212");
// for "南海諸島"
AryZipCode[25] = new Array("817","819","290");
// for "釣魚台列嶼"
AryZipCode[26] = new Array("290");
 */
function initAreaCode(areaId){

	var areaInput;
	
	areaInput = document.getElementById(areaId);
	
	areaInput.length = AryAreaCode.length;
	for (var i = 0; i < AryAreaCode.length; i++) {
		areaInput.options[i].value = AryAreaCode[i];
		areaInput.options[i].text = AryAreaCodeText[i];
	}
	areaInput.selectedIndex = 0;
	
	areaInput = null;
}

function initCounty(countyId){

	var countyInput;
	
	countyInput = document.getElementById(countyId);
	
	countyInput.innerHTML = "";
	countyInput.length = AryCounty.length;
	for (var i = 0; i < AryCounty.length; i++) {
		countyInput.options[i].value = AryCounty[i];
		countyInput.options[i].text = AryCounty[i];
	}
	countyInput.selectedIndex = 0;
	
	countyInput = null;
}

function initCountyByClass(thisObj){

	var countyInput;
	
	countyInput = thisObj;

	for(var i = 0; i < countyInput.length; i++){
		countyInput[i].innerHTML = "";
		countyInput[i].length = AryCounty.length;		
		for (var j = 0; j < AryCounty.length; j++) {
			countyInput[i].options[j].value = AryCounty[j];
			countyInput[i].options[j].text = AryCounty[j];
			countyInput[i].selectedIndex = 0;			
		}
	}	
	
	countyInput = null;
}
 /*
function initZone(countyInput, zoneInput){
  changeZone(countyInput, zoneInput);
}
*/
 /*
function initCounty2(countyInput, countyValue){
 
  countyInput.length = AryCounty.length;
  for (i = 0; i < AryCounty.length; i++) {
    countyInput.options[i].value = AryCounty[i];
    countyInput.options[i].text = AryCounty[i];
 
    if (countyValue == AryCounty[i])
      countyInput.selectedIndex = i;
  }
}
 
function initZone2(countyInput, zoneInput, post, zoneValue){
 
  selectedCountyIndex = countyInput.selectedIndex;
 
  zoneInput.length = AryZone[selectedCountyIndex].length;
  for (i = 0; i < AryZone[selectedCountyIndex].length; i++) {
    zoneInput.options[i].value = AryZone[selectedCountyIndex][i];
    zoneInput.options[i].text = AryZone[selectedCountyIndex][i];
 
    if (zoneValue == AryZone[selectedCountyIndex][i])
      zoneInput.selectedIndex = i;  
  }
 
 // showZipCode(countyInput, zoneInput, post);
}
 */
function changeZone(countyId, zoneId) {
	
	var countyInput;
	var zoneInput;
	
	countyInput = document.getElementById(countyId);
	zoneInput = document.getElementById(zoneId);
	
	selectedCountyIndex = countyInput.selectedIndex;
	
	zoneInput.innerHTML = "";
	zoneInput.length = AryZone[selectedCountyIndex].length;
	
	for (var i = 0; i < AryZone[selectedCountyIndex].length; i++) {
		zoneInput.options[i].value = AryZone[selectedCountyIndex][i];
		zoneInput.options[i].text = AryZone[selectedCountyIndex][i];
	}
	
	zoneInput.selectedIndex = 0;  
	
	countyInput = null;
	zoneInput = null;
  //showZipCode(countyInput, zoneInput, post);
}

function changeZoneByClass(countyObj, zoneObj) {
	
	var countyInput;
	var zoneInput;
	var selectedCountyIndex = 0;
	
	//countyInput = document.getElementById(countyId);
	//zoneInput = document.getElementById(zoneId);
	countyInput = countyObj;
	zoneInput = zoneObj;	
		
	for (var i = 0; i < countyInput.length; i++) {
		selectedCountyIndex = countyInput[i].selectedIndex;			
		zoneInput[i].innerHTML = "";		
		zoneInput[i].length = AryZone[selectedCountyIndex].length;
		for (var j = 0; j < AryZone[selectedCountyIndex].length; j++) {						
			zoneInput[i].options[j].value = AryZone[selectedCountyIndex][j];
			zoneInput[i].options[j].text = AryZone[selectedCountyIndex][j];
			zoneInput[i].selectedIndex = 0;
		}
	}
	
	countyInput = null;
	zoneInput = null;
  //showZipCode(countyInput, zoneInput, post);
}

function getCountyByKeyword(strKeyword){

	var strTemp = "";
	
	for (var i = 0; i < AryCounty.length; i++) {
		if( strKeyword.indexOf(AryCounty[i]) != -1 ){
			strTemp = AryCounty[i];
			break;
		}	
	}

	return strTemp;
}

 /*
function showZipCode(countyInput, zoneInput, post) {
  post.value = AryZipCode[countyInput.selectedIndex][zoneInput.selectedIndex];
}
 */

 
//==================== for zip code end =========================
// -->