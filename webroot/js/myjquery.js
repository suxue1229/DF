function tds() {
	var formParam = $("#form").serialize();// 序列化表格内容为字符串
	$.ajax({
		type : 'post',
		url : 'Main?key=' + "tds",
		data : formParam,
		cache : false,
		dataType : 'json',
		success : function(data) {
			if (data["success"] == false) {
				alert(data["errorMsg"]);
			} else {
				$("#water_tem").val(data["parT"]);
				$("#water_ph").val(data["parpH"]);
				$("#water_tds").val(data["tds"]);
				$("#water_con").val(data["parS"]);
				$("#water_Ionstr").val(data["paru"]);
				$("#water_kalinity").val(data["parcjd"]);
				$("#water_OsmoticPre").val(data["parpif"]);

				$("#Ion_table tr:eq(3) td:nth-child(2)").children("input").val(
						data["parcj1"]);
				$("#Ion_table tr:eq(3) td:nth-child(3)").children("input").val(
						data["parmj1"]);
				$("#Ion_table tr:eq(3) td:nth-child(4)").children("input").val(
						data["parzj1"]);
				$("#Ion_table tr:eq(4) td:nth-child(6)").children("input").val(
						data["right_parcj3"]);
				$("#Ion_table tr:eq(4) td:nth-child(7)").children("input").val(
						data["right_parmj3"]);
				$("#Ion_table tr:eq(4) td:nth-child(8)").children("input").val(
						data["right_parzj3"]);

				$("#parqC").val(data["parqC"]);
				$("#parqA").val(data["parqA"]);
			}
		}
	});
}
function cjd() {
	var formParam = $("#form").serialize();// 序列化表格内容为字符串
	$.ajax({
		type : 'post',
		url : 'Main?key=' + "cjd",
		data : formParam,
		cache : false,
		dataType : 'json',
		success : function(data) {
			if (data["success"] == false) {
				alert(data["errorMsg"]);
			} else {
				$("#water_tem").val(data["parT"]);
				$("#water_ph").val(data["parpH"]);
				$("#water_tds").val(data["tds"]);
				$("#water_con").val(data["parS"]);
				$("#water_Ionstr").val(data["paru"]);
				$("#water_kalinity").val(data["parcjd"]);
				$("#water_OsmoticPre").val(data["parpif"]);

				$("#Ion_table tr:eq(5) td:nth-child(6)").children("input").val(
						data["right_parcj4"]);
				$("#Ion_table tr:eq(5) td:nth-child(7)").children("input").val(
						data["right_parmj4"]);
				$("#Ion_table tr:eq(5) td:nth-child(8)").children("input").val(
						data["right_parzj4"]);

				$("#parqC").val(data["parqC"]);
				$("#parqA").val(data["parqA"]);
			}
		}
	});
}
function waterevent() {
	var formParam = $("#form").serialize();// 序列化表格内容为字符串
	$.ajax({
		type : 'post',
		url : 'Main?key=' + "tem",
		data : formParam,
		cache : false,
		dataType : 'json',
		success : function(data) {
			if (data["success"] == false) {
				alert(data["errorMsg"]);
			} else {
				$("#pro_name").val(data["name"]);
				$("#pro_design").val(data["designer"]);
				$("#pro_process").val(data["process"]);
				$("#water_tem").val(data["parT"]);
				$("#water_ph").val(data["parpH"]);
				$("#water_tds").val(data["tds"]);
				$("#water_con").val(data["parS"]);
				$("#water_Ionstr").val(data["paru"]);
				$("#water_kalinity").val(data["parcjd"]);
				$("#water_OsmoticPre").val(data["parpif"]);
			}
		}
	});
}
function orgevent() {
	var formParam = $("#form").serialize();// 序列化表格内容为字符串
	$.ajax({
		type : 'post',
		url : 'Main?key=' + "org",
		data : formParam,
		cache : false,
		dataType : 'json',
		success : function(data) {
			if (data["success"] == false) {
				alert(data["errorMsg"]);
			} else {
				if (data["index"] == 1) {
					$("#cod_C1").val(data["cod_C1"]);
				} else if (data["index"] == 2) {
					$("#org_M1").val(data["org_M1"]);
					$("#org_C1").val(data["org_C1"]);
					$("#org_M2").val(data["org_M2"]);
					$("#org_C2").val(data["org_C2"]);
				}
			}
		}
	});
}
function sysshow() {
	var formParam = $("#form").serialize();// 序列化表格内容为字符串
	$.ajax({
		type : 'post',
		url : 'Main?key=' + "system",
		data : formParam,
		cache : false,
		dataType : 'json',
		success : function(data) {
			if (data["success"] == false) {
				alert(data["errorMsg"]);
			} else {
				$("#design_Qp").val(data["pariQp"]);
				$("#design_pariY").val(data["pariY"]);
				$("#design_pariQr").val(data["pariQr"]);
				$("#design_pariJ").val(data["pariJ"]);
				$("#design_pariQf").val(data["pariQf"]);
				$("#design_pariQc").val(data["pariQc"]);
				$("#design_pariYD").val(data["pariYD"]);
			}
		}
	});
}
// 动态的创建一个table
function createShowingTable(data) {
	var tabletd = "";
	for (var i = 0; i < data["sections"]; i++) {
		tabletd = tabletd + "<td class='columntitle'>" + (i + 1) + "段</td>"
	}
	var tabletd1 = "";
	for (var i = 0; i < data["sections"]; i++) {
		tabletd1 = tabletd1
				+ "<td ><select name='modelcomboBox' class='text' ><option selected='selected'>DF301-8040(400)</option><option >DF30-8040(365)</option><option >DF30-8040(400)</option></select></td>"
	}
	var tabletd2 = "";
	for (var i = 0; i < data["sections"]; i++) {
		tabletd2 = tabletd2
				+ "<td ><input type='text' name='design_parEi' class='text' onchange='test()' value='"
				+ data["parEi" + i] + "' /></td>"
	}
	var tabletd3 = "";
	for (var i = 0; i < data["sections"]; i++) {
		tabletd3 = tabletd3
				+ "<td ><input type='text' name='design_parNVi' class='text' onchange='test()' value='"
				+ data["parNVi" + i] + "' /></td>"
	}
	var tabletd4 = "";
	for (var i = 0; i < data["sections"]; i++) {
		tabletd4 = tabletd4
				+ "<td ><input type='text' name='design_parPpi' class='text' onchange='test()' value='"
				+ data["parPpi" + i] + "' />MPa</td>"
	}
	var tabletd5 = "";
	for (var i = 0; i < data["sections"]; i++) {
		tabletd5 = tabletd5
				+ "<td ><input type='text' name='design_parDpi' class='text' onchange='test()' value='"
				+ data["parDpi" + i] + "' />MPa</td>"
	}
	var tabletd6 = "";
	for (var i = 0; i < data["sections"]; i++) {
		tabletd6 = tabletd6
				+ "<td ><input type='text' name='design_parPLi' class='text' onchange='test()' value='"
				+ data["parPLi" + i] + "' />MPa</td>"
	}
	var tableStr = "<table >";
	tableStr = tableStr + "<tr >" + "<td class='columntitle'>段数</td>" + tabletd
			+ "</tr>";
	tableStr = tableStr + "<tr >" + "<td class='columncolor'>膜元件型号</td>"
			+ tabletd1 + "</tr>";
	tableStr = tableStr + "<tr >" + "<td class='columncolor'>膜元件数</td>"
			+ tabletd2 + "</tr>";
	tableStr = tableStr + "<tr >" + "<td class='columncolor'>压力容器</td>"
			+ tabletd3 + "</tr>";
	tableStr = tableStr + "<tr >" + "<td class='columncolor'>产水背压</td>"
			+ tabletd4 + "</tr>";
	tableStr = tableStr + "<tr >" + "<td class='columncolor'>段间增压</td>"
			+ tabletd5 + "</tr>";
	tableStr = tableStr + "<tr >" + "<td class='columncolor'>段间压力损失</td>"
			+ tabletd6 + "</tr>";

	tableStr = tableStr + "</table>";
	// 添加到div中
	$("#sys_table").html(tableStr);
}
function sections(){
	var formParam = $("#form").serialize();// 序列化表格内容为字符串
	$.ajax({
		type : 'post',
		url : 'Main?key=' + "sections",
		data : formParam,
		cache : false,
		dataType : 'json',
		success : function(data) {
		createShowingTable(data);
		$("#design_pariJ").val(data["pariJ"]);
		}
	});
}
function test() {
	$("#sys_table table td").click(
			function() {
				var row = $(this).parent().index(); // 行位置
				var col = $(this).index(); // 列位置
				var formParam = $("#form").serialize();// 序列化表格内容为字符串
				$.ajax({
					type : 'post',
					url : 'Main?key=' + "table",
					data : formParam,
					cache : false,
					dataType : 'json',
					success : function(data) {
						$(
								"#sys_table table tr:eq(1) td:nth-child("
										+ (col + 1) + ")").children("select")
								.val(data["model" + (col - 1)]);
						$(
								"#sys_table table tr:eq(2) td:nth-child("
										+ (col + 1) + ")").children("input")
								.val(data["parEi" + (col - 1)]);
						$(
								"#sys_table table tr:eq(3) td:nth-child("
										+ (col + 1) + ")").children("input")
								.val(data["parNVi" + (col - 1)]);
						$(
								"#sys_table table tr:eq(4) td:nth-child("
										+ (col + 1) + ")").children("input")
								.val(data["parPpi" + (col - 1)]);
						$(
								"#sys_table table tr:eq(5) td:nth-child("
										+ (col + 1) + ")").children("input")
								.val(data["parDpi" + (col - 1)]);
						$(
								"#sys_table table tr:eq(6) td:nth-child("
										+ (col + 1) + ")").children("input")
								.val(data["parPLi" + (col - 1)]);
						$("#design_pariJ").val(data["pariJ"]);
					}
				});
			})
}
function tableevent() {
	$("#Ion_table td").click(
			function() {
				var row = $(this).parent().index(); // 行位置
				var col = $(this).index() + 1; // 列位置
				var formParam = $("#form").serialize();// 序列化表格内容为字符串
				var value = $(this).children("input").val();
				$.ajax({
					type : 'post',
					url : 'Main?key=' + "iontable" + "&state=" + col,
					data : formParam,
					cache : false,
					dataType : 'json',
					success : function(data) {
						for (var i = 0; i < 11; i++) {
							$(
									"#Ion_table tr:eq(" + (i + 2)
											+ ") td:nth-child(2)").children(
									"input").val(data["parcj" + i]);
							$(
									"#Ion_table tr:eq(" + (i + 2)
											+ ") td:nth-child(3)").children(
									"input").val(data["parmj" + i]);
							$(
									"#Ion_table tr:eq(" + (i + 2)
											+ ") td:nth-child(4)").children(
									"input").val(data["parzj" + i]);
						}
						for (var i = 0; i < data["Eionlength"] - 11; i++) {
							$(
									"#Ion_table tr:eq(" + (i + 2)
											+ ") td:nth-child(6)").children(
									"input").val(data["right_parcj" + i]);
							$(
									"#Ion_table tr:eq(" + (i + 2)
											+ ") td:nth-child(7)").children(
									"input").val(data["right_parmj" + i]);
							$(
									"#Ion_table tr:eq(" + (i + 2)
											+ ") td:nth-child(8)").children(
									"input").val(data["right_parzj" + i]);
						}
						$("#parqC").val(data["parqC"]);
						$("#parqA").val(data["parqA"]);
						$("#parSCaCO3").val(data["parSCaCO3"]);
						$("#parSBaSO4").val(data["parSBaSO4"]);
						$("#parSCaSO4").val(data["parSCaSO4"]);
						$("#parSSrSO4").val(data["parSSrSO4"]);
						$("#parSCa3PO42").val(data["parSCa3PO42"]);
						$("#parSSrSO4").val(data["parSCaF2"]);
						if ($("#parSBaSO4").val() > 100) {
							$("#parSBaSO4").css("background", "red"); // 点击为红色。
						}
						if ($("#parSCaSO4").val() > 100) {
							$("#parSCaSO4").css("background", "red"); // 点击为红色。
						}
						if ($("#parSCaSO4").val() > 100) {
							$("#parSCaSO4").css("background", "red"); // 点击为红色。
						}
						if ($("#parSCa3PO42").val() > 100) {
							$("#parSCa3PO42").css("background", "red"); // 点击为红色。
						}
						if ($("#parSSrSO4").val() > 100) {
							$("#parSSrSO4").css("background", "red"); // 点击为红色。
						}
						if ($("#parSSrSO4").val() > 100) {
							$("#parSSrSO4").css("background", "red"); // 点击为红色。
						}
						$("#water_tds").val(data["tds"]);
						$("#water_con").val(data["parS"]);
						$("#water_Ionstr").val(data["paru"]);
						$("#water_kalinity").val(data["parcjd"]);
						$("#water_OsmoticPre").val(data["parpif"]);
					}
				});
			})
}
function nTabs(thisObj, Num) {
	if (thisObj.className == "active")
		return;
	var tabObj = thisObj.parentNode.id;
	var tabList = document.getElementById(tabObj).getElementsByTagName("li");
	for (i = 0; i < tabList.length; i++) {
		if (i == Num) {
			thisObj.className = "active"
			document.getElementById(tabObj + "_Content" + i).style.display = "block";
		} else {
			tabList[i].className = "normal";
			document.getElementById(tabObj + "_Content" + i).style.display = "none";
		}
	}
}
$(document).ready(
		function() {
			document.getElementById("myTab0_Content0").style.display = "block";
			document.getElementById("myTab0_Content1").style.display = "none";
			document.getElementById("myTab0_Content2").style.display = "none";
			$(".selectorg td").children("input[type='text']").attr("disabled",
					true);
			$(".selectcod td").children("input[type='text']").attr("disabled",
					false);
			$(".radioItem").change(
					function() {
						var type = $("input[name='org']:checked").val();
						if (type == '1') {
							$(".selectorg td").children("input[type='text']")
									.attr("disabled", true);
							$(".selectcod td").children("input[type='text']")
									.attr("disabled", false);
						} else if (type == '2') {
							$(".selectorg td").children("input[type='text']")
									.attr("disabled", false);
							$(".selectcod td").children("input[type='text']")
									.attr("disabled", true);
						}
					});

			$("input").keydown(function(event) {
				if (event.keyCode == 13)
					return false;
			});
			sections();
		});
function changebackground() {
	$("#out_table4 td.lab").each(function() {
		var tdNum = Number($(this).html());
		if (tdNum > 100) {
			$(this).css("background-color", "red");
		}
		$(this).html($(this).html() + " %");
	});
	$("#out_table4 td.lab1").each(function() {
		var tdNum1 = Number($(this).html());
		if (tdNum1 > 0) {
			$(this).css("background-color", "red");
		}
	});
	$("#out_table3 td.lab_parFi").each(function() {
		var tdNum2 = Number($(this).html());
		if (tdNum2 > 30) {
			$(this).css("background-color", "red");
		}
	});
	$("#out_table3 td.lab_fparQ").each(function() {
		var tdNum3 = Number($(this).html());
		if (tdNum3 > 15) {
			$(this).css("background-color", "red");
		}
	});
	$("#out_table3 td.lab_cparQ").each(function() {
		var tdNum4 = Number($(this).html());
		if (tdNum4 < 3) {
			$(this).css("background-color", "red");
		}
	});
}
function calculate() {
	var formParam = $("#form").serialize();// 序列化表格内容为字符串
	$.ajax({
		type : 'post',
		url : 'Main?key=' + "calc",
		data : formParam,
		cache : false,
		dataType : 'json',
		success : function(data) {
			if (data["success"] == false) {
				alert(data["errorMsg"]);
			} else {
				window.open('result.jsp');//在新窗口中打开result页面
			}
		}
	});
}
