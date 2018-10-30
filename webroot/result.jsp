<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="engine.MSystem" import="engine.EIon"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="css/style.css" type="text/css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="js/myjquery.js"></script>
<title>输出显示</title>
</head>
<body onload="changebackground()">
	<%
		MSystem msystem = (MSystem) request.getSession().getAttribute("ms");
		java.text.DecimalFormat df2 = new java.text.DecimalFormat("0.00");
	%>
	<form>
		<div class="topmenu">
			<img src="Images/logo.png" height="80">
		</div>

		<div class="clearfix">
			<div class="column sidemenu">
				<ul id="myTab0">
					<li class="active" onclick="nTabs(this,0);">系统报告输出</li>
					<li class="normal" onclick="nTabs(this,1);">水质报告输出</li>
					<li class="normal" onclick="nTabs(this,2);">工作条件输出</li>
					<li class="normal" onclick="nTabs(this,3);">元件结垢预测</li>
				</ul>
			</div>

			<div class="column content">
				<div id="myTab0_Content0">
					<div class="con" align="center">
						<table id="out_table1">
							<tbody>
								<%
									String[] name1 = {"排列", "压力容器数", "膜元件数", "进水压力(MPa)", "产水压力(MPa)", "浓水压力(MPa)", "进水流量(m3/h)", "产水流量(m3/h)",
											"浓水流量(m3/h)", "通量(LMH)", "最大水通量(LMH)"};
								%>
								<tr>
									<%
										for (int i = 0; i < name1.length; i++) {
									%>
									<td class="columncolor"><%=name1[i]%></td>
									<%
										}
									%>
								</tr>
								<%
									for (int i = 1; i < msystem.section() + 1; i++) {
								%>
								<tr>
									<td class="columncolor"><%=i%></td>
									<td class="lab"><%=msystem.sections()[i - 1].parNVi%></td>
									<td class="lab"><%=msystem.sections()[i - 1].parEi%></td>
									<td class="lab"><%=df2.format(msystem.sections()[i - 1].streamf.parP)%></td>
									<td class="lab"><%=df2.format(msystem.sections()[i - 1].streamp.parP)%></td>
									<td class="lab"><%=df2.format(msystem.sections()[i - 1].streamc.parP)%></td>
									<td class="lab"><%=df2.format(msystem.sections()[i - 1].streamf.parQ)%></td>
									<td class="lab"><%=df2.format(msystem.sections()[i - 1].streamp.parQ)%></td>
									<td class="lab"><%=df2.format(msystem.sections()[i - 1].streamc.parQ)%></td>
									<td class="lab"><%=df2.format(msystem.sections()[i - 1].parFi())%></td>
									<td class="lab"><%=df2.format(msystem.sections()[i - 1].parFimax())%></td>
								</tr>
								<%
									}
								%>
							</tbody>
						</table>
					</div>
				</div>
				<div id="myTab0_Content1" class="none">
					<div class="con" align="center">
						<table id="out_table2">
							<tbody>
								<tr>
									<td class="columncolor">指标</td>
									<%
										for (int i = 0; i < msystem.section(); i++) {
									%>
									<td class="columncolor"><%=(i + 1) + "段 产水(mg/L)"%></td>
									<td class="columncolor"><%=(i + 1) + "段 浓水(mg/L)"%></td>
									<%
										}
									%>
									<td class="columncolor">总产水(mg/L)</td>
								</tr>
								<%
									for (int m = 0; m < EIon.values().length; m++) {
								%>
								<tr>
									<td class="columncolor"><%=msystem.sections()[0].streamp.ion(EIon.values()[m]).name%></td>
									<%
										for (int n = 0; n < msystem.section(); n++) {
									%>
									<td class="lab"><%=df2.format(msystem.sections()[n].streamp.ion(EIon.values()[m]).parcj())%></td>
									<td class="lab"><%=df2.format(msystem.sections()[n].streamc.ion(EIon.values()[m]).parcj())%></td>
									<%
										}
									%>
									<td class="lab"><%=df2.format(msystem.streamp.ion(EIon.values()[m]).parcj())%></td>
								</tr>
								<%
									}
								%>
								<tr>
									<td class="columncolor">TDS</td>
									<%
										for (int t = 0; t < msystem.section(); t++) {
									%>
									<td class="lab"><%=df2.format(msystem.sections()[t].streamp.tds())%></td>
									<td class="lab"><%=df2.format(msystem.sections()[t].streamc.tds())%></td>
									<%
										}
									%>
									<td class="lab"><%=df2.format(msystem.streamp.tds())%></td>
								</tr>
								<tr>
									<td class="columncolor">pH</td>
									<%
										for (int t = 0; t < msystem.section(); t++) {
									%>
									<td class="lab"><%=df2.format(msystem.sections()[t].streamp.parpH())%></td>
									<td class="lab"><%=df2.format(msystem.sections()[t].streamc.parpH())%></td>
									<%
										}
									%>
									<td class="lab"><%=df2.format(msystem.streamp.parpH())%></td>
								</tr>
								<%
									for (int j = 0; j < msystem.streams.cods().length; j++) {
								%>
								<tr>
									<td class="columncolor"><%=msystem.sections()[0].streamp.cods()[j].name%></td>
									<%
										for (int t = 0; t < msystem.section(); t++) {
									%>
									<td class="lab"><%=df2.format(msystem.sections()[t].streamp.cods()[j].parcj)%></td>
									<td class="lab"><%=df2.format(msystem.sections()[t].streamc.cods()[j].parcj)%></td>
									<%
										}
									%>
									<td class="lab"><%=df2.format(msystem.streamp.cods()[j].parcj)%></td>

								</tr>
								<%
									}
								%>
							</tbody>
						</table>
					</div>
				</div>
				<div id="myTab0_Content2" class="none">
					<div class="con" align="center">
						<table id="out_table3">
							<%
								String[] name2 = {"元件位置", "回收率", "膜通量(LMH)", "进水流量(m3/h)", "产水流量(m3/h)", "浓水流量(m3/h)"};
							%>
							<tbody>
								<tr>
									<%
										for (int j = 0; j < 6; j++) {
									%>
									<td class="columncolor"><%=name2[j]%></td>
									<%
										}
									%>
								</tr>
								<%
									for (int i = 0; i < msystem.section(); i++) {
										for (int j = 0; j < msystem.sections()[i].parEi; j++) {
								%>
								<tr>
									<td class="columncolor">第 <%=(i + 1)%> 段 第<%=(j + 1)%> 支
									</td>
									<td class="lab"><%=df2.format(msystem.sections()[i].branes()[j].parYt())%></td>
									<td class="lab_parFi"><%=df2.format(msystem.sections()[i].branes()[j].parFi())%></td>
									<td class="lab_fparQ"><%=df2.format(msystem.sections()[i].branes()[j].streamf.parQ)%></td>
									<td class="lab"><%=df2.format(msystem.sections()[i].branes()[j].streamp.parQ)%></td>
									<td class="lab_cparQ"><%=df2.format(msystem.sections()[i].branes()[j].streamc.parQ)%></td>
									<%
										}
									%>
								</tr>
								<%
									}
								%>

							</tbody>
						</table>
					</div>
				</div>
				<div id="myTab0_Content3" class="none">
					<div class="con" align="center">
						<table id="out_table4">
							<tbody>
								<%
									String[] name3 = {"元件位置", "CaCO3(L.I.)", "CaSO4", "Ca3(PO4)2", "BaSO4", "SrSO4", "CaF2"};
								%>
								<%
									for (int j = 0; j < 7; j++) {
								%>
								<td class="columncolor"><%=name3[j]%></td>
								<%
									}
								%>
								<%
									for (int i = 0; i < msystem.section(); i++) {
										for (int j = 0; j < msystem.sections()[i].parEi; j++) {
								%>
								<tr>
									<td class="columncolor">第 <%=(i + 1)%> 段 第 <%=(j + 1)%> 支
									</td>
									<td class="lab1"><%=df2.format(msystem.sections()[i].branes()[j].streamc.parSCaCO3())%></td>
									<td class="lab"><%=df2.format(msystem.sections()[i].branes()[j].streamc.parSCaSO4() * 100)%></td>
									<td class="lab"><%=df2.format(msystem.sections()[i].branes()[j].streamc.parSCa3PO42() * 100)%></td>
									<td class="lab"><%=df2.format(msystem.sections()[i].branes()[j].streamc.parSBaSO4() * 100)%></td>
									<td class="lab"><%=df2.format(msystem.sections()[i].branes()[j].streamc.parSSrSO4() * 100)%></td>
									<td class="lab"><%=df2.format(msystem.sections()[i].branes()[j].streamc.parSCaF2() * 100)%></td>
								</tr>
								<%
									}
									}
								%>

							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>

		<script type="text/javascript">
			function nTabs(thisObj, Num) {
				if (thisObj.className == "active")
					return;
				var tabObj = thisObj.parentNode.id;
				var tabList = document.getElementById(tabObj)
						.getElementsByTagName("li");
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

			$(document)
					.ready(
							function() {
								document.getElementById("myTab0_Content0").style.display = "block";
								document.getElementById("myTab0_Content1").style.display = "none";
								document.getElementById("myTab0_Content2").style.display = "none";
								document.getElementById("myTab0_Content3").style.display = "none";
							});
		</script>
	</form>
</body>

</html>