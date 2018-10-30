<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="engine.MSystem" import="engine.EIon"
	import="engine.EIon,java.text.SimpleDateFormat"
	import="com.google.gson.Gson"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="css/style.css" type="text/css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="js/myjquery.js"></script>
<title>DF 软件</title>
</head>
<body>
	<%
		MSystem msystem = new MSystem();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	%>
	<form action="Main" method="post" name="Main" id="form">
		<div class="topmenu">
			<img src="Images/logo.png" height="80">
		</div>
		<div class="clearfix">
			<div class="column sidemenu">
				<ul id="myTab0">
					<li class="active" onclick="nTabs(this,0);">项目信息</li>
					<li class="normal" onclick="nTabs(this,1);">水质指标</li>
					<li class="normal" onclick="nTabs(this,2);">系统设计</li>
				</ul>
			</div>

			<div class="column content">
				<div id="myTab0_Content0">
					<div class="con" align="center">
						<table id="pro_table">
							<tbody>
								<tr>
									<td class="columntitle" colspan="4">项目信息</td>
								</tr>
								<tr>
									<td class="columncolor">项目名称</td>
									<td><input type="text" name="pro_name" class="text"
										onchange="waterevent()" id="pro_name" value=<%=msystem.name%>></td>
									<td class="columncolor">设计者</td>
									<td><input type="text" name="pro_design" class="text"
										onchange="waterevent()" id="pro_design"
										value=<%=msystem.designer%>></td>
								</tr>
								<tr>
									<td class="columncolor">项目工艺</td>
									<td><input type="text" name="pro_process" class="text"
										onchange="waterevent()" id="pro_process"
										value=<%=msystem.process%>></td>
									<td class="columncolor">日期</td>
									<td><input type="text" class="text" id="date" name="date"
										disabled="disabled" value=<%=sdf.format(msystem.date)%>></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div id="myTab0_Content1" class="none">
					<div class="con" align="center">
						<table id="water_table">
							<tbody>
								<tr>
									<td class="columntitle" colspan="4">进水参数</td>
								</tr>
								<tr>
									<td class="columncolor">温度</td>
									<td><input type="text" name="water_tem" class="text"
										id="water_tem" onchange="waterevent()"
										value=<%=String.format("%.1f", msystem.streams.parT())%>></td>
									<td class="columncolor"></td>
									<td></td>
								</tr>
								<tr>
									<td class="columncolor">PH</td>
									<td><input type="text" name="water_ph" class="text"
										id="water_ph" onchange="waterevent()"
										value=<%=String.format("%.2f", msystem.streams.parpH())%>></td>
									<td class="columncolor">电导率</td>
									<td><input type="text" name="water_con" class="text"
										disabled="disabled" id="water_con"
										value=<%=String.format("%.2f", msystem.streams.parS())%>></td>
								</tr>
								<tr>
									<td class="columncolor">TDS</td>
									<td><input type="text" name="water_tds" class="text"
										id="water_tds" onchange="tds()"
										value=<%=String.format("%.1f", msystem.streams.tds())%>></td>
									<td class="columncolor">离子强度</td>
									<td><input type="text" name="water_Ionstr" class="text"
										disabled="disabled" id="water_Ionstr"
										value=<%=String.format("%.3f", msystem.streams.paru())%>></td>
								</tr>
								<tr>
									<td class="columncolor">碱度</td>
									<td><input type="text" name="water_kalinity" class="text"
										id="water_kalinity" onchange="cjd()"
										value=<%=String.format("%.1f", msystem.streams.parcjd())%>></td>
									<td class="columncolor">渗透压</td>
									<td><input type="text" name="water_OsmoticPre"
										disabled="disabled" class="text" id="water_OsmoticPre"
										value=<%=String.format("%.2f", msystem.streams.parpif())%>></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div id="iontable">
						<div class="con" align="center">
							<table id="Ion_table" onclick="tableevent()">
								<tbody>
									<%
										String[] name = { "阳离子", "质量浓度(mg/L)", "摩尔浓度(mmol/L)", "当量浓度(meq/L)", "阴离子", "质量浓度(mg/L)", "摩尔浓度(mmol/L)",
												"当量浓度(meq/L)" };
									%>
									<tr>
										<td class="columntitle" colspan="8">离子信息</td>
									</tr>
									<tr>
										<%
											for (int i = 0; i < 8; i++) {
										%><td class="columncolor"><%=name[i]%></td>
										<%
											}
										%>
									</tr>
									<%
										for (int i = 0; i < EIon.values().length - 11; i++) {
									%>
									<tr>
										<td class="columncolor"><%=msystem.streams.ion(EIon.values()[i]).name%></td>
										<td><input type="text" class="text" name="ion_parcj"
											value=<%=String.format("%.2f", msystem.streams.ion(EIon.values()[i]).parcj())%>></td>
										<td><input type="text" class="text" name="ion_parmj"
											value=<%=String.format("%.2f", msystem.streams.ion(EIon.values()[i]).parmj() * 1000)%>></td>
										<td><input type="text" class="text" name="ion_parzj"
											value=<%=String.format("%.2f", msystem.streams.ion(EIon.values()[i]).parzj())%>></td>
										<td class="columncolor"><%=msystem.streams.ion(EIon.values()[i + 11]).name%></td>
										<td><input type="text" class="text" name="rightion_parcj"
											value=<%=String.format("%.2f", msystem.streams.ion(EIon.values()[i + 11]).parcj())%>></td>
										<td><input type="text" class="text" name="rightion_parmj"
											value=<%=String.format("%.2f", msystem.streams.ion(EIon.values()[i + 11]).parmj() * 1000)%>></td>
										<td><input type="text" class="text" name="rightion_parzj"
											value=<%=String.format("%.2f", msystem.streams.ion(EIon.values()[i + 11]).parzj())%>></td>
									</tr>
									<%
										}
									%>
									<%
										for (int i = EIon.values().length - 11; i < 11; i++) {
									%>
									<tr>
										<td class="columncolor"><%=msystem.streams.ion(EIon.values()[i]).name%></td>
										<td><input type="text" class="text" name="ion_parcj"
											value=<%=String.format("%.2f", msystem.streams.ion(EIon.values()[i]).parcj())%>></td>
										<td><input type="text" class="text" name="ion_parmj"
											value=<%=String.format("%.2f", msystem.streams.ion(EIon.values()[i]).parmj() * 1000)%>></td>
										<td><input type="text" class="text" name="ion_parzj"
											value=<%=String.format("%.2f", msystem.streams.ion(EIon.values()[i]).parzj())%>></td>
										<td class="columncolor"></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<%
										}
									%>
									<tr>
										<td class="columncolor">总计</td>
										<td></td>
										<td></td>
										<td><input type="text" class="text" name="parqC"
											id="parqC" disabled="disabled"
											value=<%=String.format("%.2f", msystem.streams.parqC())%>>
											meq/L</td>
										<td class="columncolor">总计</td>
										<td></td>
										<td></td>
										<td><input type="text" class="text" name="parqC"
											id="parqA" disabled="disabled"
											value=<%=String.format("%.2f", msystem.streams.parqA())%>>meq/L</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div class="con" align="center">
						<table id="Satur_table">
							<tbody>
								<tr>
									<td class="columntitle" colspan="4">饱和度</td>
								</tr>
								<tr>
									<td class="columncolor">CaCO3(L.I.)</td>
									<td><input type="text" id="parSCaCO3" class="text"
										disabled="disabled"
										value=<%=String.format("%.2f", msystem.streams.parSCaCO3())%>></td>
									<td class="columncolor">BaSO4</td>
									<td><input type="text" class="text" id="parSBaSO4"
										disabled="disabled"
										value=<%=String.format("%.2f", msystem.streams.parSBaSO4() * 100)%>>%</td>
								</tr>
								<tr>
									<td class="columncolor">CaSO4</td>
									<td><input type="text" id="parSCaSO4" class="text"
										disabled="disabled"
										value=<%=String.format("%.2f", msystem.streams.parSCaSO4() * 100)%>>%</td>
									<td class="columncolor">SrSO4</td>
									<td><input type="text" id="parSSrSO4" class="text"
										disabled="disabled"
										value=<%=String.format("%.2f", msystem.streams.parSSrSO4() * 100)%>>%</td>
								</tr>
								<tr>
									<td class="columncolor">Ca3(PO4)2</td>
									<td><input type="text" id="parSCa3PO42" class="text"
										disabled="disabled"
										value=<%=String.format("%.2f", msystem.streams.parSCa3PO42() * 100)%>>%</td>
									<td class="columncolor">CaF2</td>
									<td><input type="text" id="parSCaF2" class="text"
										disabled="disabled"
										value=<%=String.format("%.2f", msystem.streams.parSCaF2() * 100)%>>%</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div id="orgtable">
						<div class="con">
							<table id="org_table">
								<tbody>
									<tr>
										<td class="columntitle" colspan="8">有机物信息</td>
									</tr>
									<tr class="selectcod">
										<td><input type="radio" name="org" checked="checked"
											class="radioItem" value="1"></td>
										<td class="columncolor">COD</td>
										<td colspan="7"><input type="text" class="text"
											name="cod_C1" id="cod_C1" onchange="orgevent()"
											value=<%=String.format("%.2f", msystem.streams.cods()[0].parcj)%>>mg/L</td>
									</tr>
									<tr>
									<tr class="selectorg">
										<td rowspan="2"><input type="radio" name="org"
											class="radioItem" value="2"></td>
										<td class="columncolor" rowspan="2">有机物1</td>
										<td>分子质量(M1)</td>
										<td><input type="text" name="org_M1" id="org_M1"
											class="text" onchange="orgevent()"
											value=<%=String.format("%.2f", msystem.streams.cods()[0].parMj)%>></td>
										<td class="columncolor" rowspan="2">有机物2</td>
										<td>分子质量(M2)</td>
										<td><input type="text" name="org_M2" id="org_M2"
											class="text" onchange="orgevent()"
											value=<%=String.format("%.2f", msystem.streams.cods()[0].parMj)%>></td>
									</tr>
									<tr class="selectorg">
										<td>浓度</td>
										<td><input type="text" name="org_C1" class="text"
											id="org_C1" onchange="orgevent()"
											value=<%=String.format("%.2f", msystem.streams.cods()[0].parcj)%>>mg/L</td>
										<td>浓度</td>
										<td><input type="text" name="org_C2" class="text"
											id="org_C2" onchange="orgevent()"
											value=<%=String.format("%.2f", msystem.streams.cods()[0].parcj)%>>mg/L</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div id="myTab0_Content2" class="none">
					<div>
						<div class="con" align="center">
							<table id="systable">
								<tbody>
									<tr>
										<td class="columntitle" colspan="6">水量设置</td>
									</tr>
									<tr>
										<td class="columncolor">产水流量</td>
										<td><input type="text" name="design_Qp" class="text"
											onchange="sysshow()" id="design_Qp"
											value=<%=String.format("%.2f", msystem.pariQp)%>>m3/h</td>
										<td class="columncolor">平均水通量</td>
										<td><input type="text" name="design_pariJ" class="text"
											id="design_pariJ" disabled="disabled"
											value=<%=String.format("%.2f", msystem.pariJ())%>>LMH</td>
										<td class="columncolor">进水流量</td>
										<td><input type="text" name="design_pariQf" class="text"
											disabled="disabled" id="design_pariQf"
											value=<%=String.format("%.2f", msystem.pariQf())%>>m3/h</td>
									</tr>
									<tr>
										<td class="columncolor">产水回收率</td>
										<td><input type="text" name="design_pariY" class="text"
											id="design_pariY" onchange="sysshow()"
											value=<%=String.format("%.2f", msystem.pariY)%>>%</td>
										<td class="columncolor">浓水流量</td>
										<td><input type="text" name="design_pariQc" class="text"
											disabled="disabled" id="design_pariQc"
											value=<%=String.format("%.2f", msystem.pariQc())%>>m3/h</td>
										<td class="columncolor">DF膜回收率</td>
										<td><input type="text" name="design_pariYD" class="text"
											disabled="disabled" id="design_pariYD"
											value=<%=String.format("%.2f", msystem.pariYD())%>>%</td>
									</tr>
									<tr>
										<td class="columncolor">循环流量</td>
										<td><input type="text" name="design_pariQr" class="text"
											id="design_pariQr" onchange="sysshow()"
											value=<%=String.format("%.2f", msystem.pariQr)%>>m3/h</td>
										<td class="columncolor">总段数</td>
										<td><input type="text" name="design_sections"
											id="design_sections" class="text" onblur="sections()"
											value=<%=String.format("%d", msystem.section())%>></td>
										<td></td>
										<td></td>
									</tr>

								</tbody>
							</table>
						</div>
						<div class="con" align="center" id="sys_table"></div>
					</div>
					<div align="right">
						<input type="button" value="计算" name="calc" onclick="calculate()">
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>