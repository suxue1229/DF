package webapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import engine.EIon;
import engine.MSystem;

/**
 * Servlet implementation class Pro_table
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Gson gson = new GsonBuilder().serializeNulls().create();// 如果想输出空值属性，可以试试

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<Object, Object> map = new HashMap<>();
		HttpSession session = request.getSession();
		map.put("success", true);
		try {
			response.setContentType("text/html; charset=gb2312");
			String key = request.getParameter("key");
			MSystem ms = new MSystem();
			String[] parcj = request.getParameterValues("ion_parcj");
			String[] right_parcj = request.getParameterValues("rightion_parcj");
			String[] parmj = request.getParameterValues("ion_parmj");
			String[] right_parmj = request.getParameterValues("rightion_parmj");
			String[] parzj = request.getParameterValues("ion_parzj");
			String[] right_parzj = request.getParameterValues("rightion_parzj");

			String[] model = request.getParameterValues("modelcomboBox");
			String[] parEi_str = request.getParameterValues("design_parEi");
			String[] parNVi_str = request.getParameterValues("design_parNVi");
			String[] parPpi_str = request.getParameterValues("design_parPpi");
			String[] parDpi_str = request.getParameterValues("design_parDpi");
			String[] parPLi_str = request.getParameterValues("design_parPLi");
			if (key.equals("calc")) {
				// 项目信息
				ms.name = request.getParameter("pro_name");
				ms.designer = request.getParameter("pro_design");
				ms.process = request.getParameter("pro_process");
				// 水质参数
				ms.streams.parT(Double.parseDouble(request.getParameter("water_tem")));
				ms.streams.parpH(Double.parseDouble(request.getParameter("water_ph")));
				ms.streams.tds(Double.parseDouble(request.getParameter("water_tds")));
				ms.streams.parcjd(Double.parseDouble(request.getParameter("water_kalinity")));
				// 离子表
				for (int i = 0; i < 11; i++) {
					ms.streams.ion(EIon.values()[i]).parcj(Double.parseDouble(parcj[i]));
					ms.streams.ion(EIon.values()[i]).parmj(Double.parseDouble(parmj[i]) / 1000);
					ms.streams.ion(EIon.values()[i]).parzj(Double.parseDouble(parzj[i]));
				}
				for (int i = 0; i < EIon.values().length - 11; i++) {
					ms.streams.ion(EIon.values()[i + 11]).parcj(Double.parseDouble(right_parcj[i]));
					ms.streams.ion(EIon.values()[i + 11]).parmj(Double.parseDouble(right_parmj[i]) / 1000);
					ms.streams.ion(EIon.values()[i + 11]).parzj(Double.parseDouble(right_parzj[i]));
				}
				// 有机物信息
				int index = Integer.parseInt(request.getParameter("org"));
				map.put("index", index);
				if (index == 1) {
					ms.streams.codmode(true);
					ms.streams.cods()[0].parcj = Double.parseDouble(request.getParameter("cod_C1"));
				} else if (index == 2) {
					ms.streams.codmode(false);
					for (int j = 0; j < ms.streams.cods().length; j++) {
						ms.streams.cods()[j].parMj = Double.parseDouble(request.getParameter("org_M" + (j + 1)));
						ms.streams.cods()[j].parcj = Double.parseDouble(request.getParameter("org_C" + (j + 1)));
					}
				}
				// 水量信息
				ms.pariQp = Double.parseDouble(request.getParameter("design_Qp"));
				ms.pariY = Double.parseDouble(request.getParameter("design_pariY"));
				ms.pariQr = Double.parseDouble(request.getParameter("design_pariQr"));
				ms.section(Integer.parseInt(request.getParameter("design_sections")));
				for (int i = 0; i < ms.section(); i++) {
					ms.sections()[i].model = model[i];
					ms.sections()[i].parEi = Integer.parseInt(parEi_str[i]);
					ms.sections()[i].parNVi = Integer.parseInt(parNVi_str[i]);
					ms.sections()[i].parPpi = Double.parseDouble(parPpi_str[i]);
					ms.sections()[i].parDpi = Double.parseDouble(parDpi_str[i]);
					ms.sections()[i].parPLi = Double.parseDouble(parPLi_str[i]);
				}
				ms.calculate();
				session.setAttribute("ms", ms);
			} else if (key.equals("tds")) {
				ms.streams.parT(Double.parseDouble(request.getParameter("water_tem")));
				ms.streams.parpH(Double.parseDouble(request.getParameter("water_ph")));
				ms.streams.tds(Double.parseDouble(request.getParameter("water_tds")));
				ms.streams.parcjd(Double.parseDouble(request.getParameter("water_kalinity")));
				map.put("parT", String.format("%.1f", ms.streams.parT()));// 温度
				map.put("parpH", String.format("%.2f", ms.streams.parpH()));// pH
				map.put("tds", String.format("%.1f", ms.streams.tds()));// tds
				map.put("parS", String.format("%.2f", ms.streams.parS()));// 电导率
				map.put("paru", String.format("%.3f", ms.streams.paru()));// 离子强度
				map.put("parcjd", String.format("%.1f", ms.streams.parcjd()));// 碱度
				map.put("parpif", String.format("%.2f", ms.streams.parpif()));// 渗透压
				map.put("parcj1", String.format("%.2f", ms.streams.ion(EIon.values()[1]).parcj()));
				map.put("parmj1", String.format("%.2f", ms.streams.ion(EIon.values()[1]).parmj() * 1000));
				map.put("parzj1", String.format("%.2f", ms.streams.ion(EIon.values()[1]).parzj()));
				map.put("right_parcj3", String.format("%.2f", ms.streams.ion(EIon.values()[13]).parcj()));
				map.put("right_parmj3", String.format("%.2f", ms.streams.ion(EIon.values()[13]).parmj() * 1000));
				map.put("right_parzj3", String.format("%.2f", ms.streams.ion(EIon.values()[13]).parzj()));
				map.put("parqC", String.format("%.2f", ms.streams.parqC()));
				map.put("parqA", String.format("%.2f", ms.streams.parqA()));
			} else if (key.equals("cjd")) {
				for (int i = 0; i < 11; i++) {
					ms.streams.ion(EIon.values()[i]).parcj(Double.parseDouble(parcj[i]));
					ms.streams.ion(EIon.values()[i]).parmj(Double.parseDouble(parmj[i]) / 1000);
					ms.streams.ion(EIon.values()[i]).parzj(Double.parseDouble(parzj[i]));
					map.put("parcj" + i, String.format("%.2f", ms.streams.ion(EIon.values()[i]).parcj()));
					map.put("parmj" + i, String.format("%.2f", ms.streams.ion(EIon.values()[i]).parmj() * 1000));
					map.put("parzj" + i, String.format("%.2f", ms.streams.ion(EIon.values()[i]).parzj()));
				}
				for (int i = 0; i < EIon.values().length - 11; i++) {
					ms.streams.ion(EIon.values()[i + 11]).parcj(Double.parseDouble(right_parcj[i]));
					ms.streams.ion(EIon.values()[i + 11]).parmj(Double.parseDouble(right_parmj[i]) / 1000);
					ms.streams.ion(EIon.values()[i + 11]).parzj(Double.parseDouble(right_parzj[i]));
					map.put("right_parcj" + i, String.format("%.2f", ms.streams.ion(EIon.values()[i + 11]).parcj()));
					map.put("right_parmj" + i,
							String.format("%.2f", ms.streams.ion(EIon.values()[i + 11]).parmj() * 1000));
					map.put("right_parzj" + i, String.format("%.2f", ms.streams.ion(EIon.values()[i + 11]).parzj()));
				}

				ms.streams.parT(Double.parseDouble(request.getParameter("water_tem")));
				ms.streams.parpH(Double.parseDouble(request.getParameter("water_ph")));
				ms.streams.parcjd(Double.parseDouble(request.getParameter("water_kalinity")));
				map.put("parT", String.format("%.1f", ms.streams.parT()));// 温度
				map.put("parpH", String.format("%.2f", ms.streams.parpH()));// pH
				map.put("right_parcj4", String.format("%.2f", ms.streams.ion(EIon.values()[14]).parcj()));
				map.put("right_parmj4", String.format("%.2f", ms.streams.ion(EIon.values()[14]).parmj() * 1000));
				map.put("right_parzj4", String.format("%.2f", ms.streams.ion(EIon.values()[14]).parzj()));
				map.put("parqC", String.format("%.2f", ms.streams.parqC()));
				map.put("parqA", String.format("%.2f", ms.streams.parqA()));
				map.put("tds", String.format("%.1f", ms.streams.tds()));// tds
				map.put("parS", String.format("%.2f", ms.streams.parS()));// 电导率
				map.put("paru", String.format("%.3f", ms.streams.paru()));// 离子强度
				map.put("parcjd", String.format("%.1f", ms.streams.parcjd()));// 碱度
				map.put("parpif", String.format("%.2f", ms.streams.parpif()));// 渗透压
			} else if (key.equals("tem")) {
				// 项目信息
				ms.name = request.getParameter("pro_name");
				ms.designer = request.getParameter("pro_design");
				ms.process = request.getParameter("pro_process");
				map.put("name", ms.name);
				map.put("designer", ms.designer);
				map.put("process", ms.process);
				ms.streams.tds(Double.parseDouble(request.getParameter("water_tds")));
				map.put("tds", String.format("%.1f", ms.streams.tds()));// tds
				ms.streams.parT(Double.parseDouble(request.getParameter("water_tem")));
				map.put("parT", String.format("%.1f", ms.streams.parT()));// 温度
				ms.streams.parpH(Double.parseDouble(request.getParameter("water_ph")));
				map.put("parpH", String.format("%.2f", ms.streams.parpH()));// pH
				if (request.getParameter("water_kalinity").equals("0.1")) {
					map.put("parcjd", String.format("%.1f", ms.streams.parcjd()));// 碱度
				} else {
					for (int i = 0; i < 11; i++) {
						ms.streams.ion(EIon.values()[i]).parcj(Double.parseDouble(parcj[i]));
						ms.streams.ion(EIon.values()[i]).parmj(Double.parseDouble(parmj[i]) / 1000);
						ms.streams.ion(EIon.values()[i]).parzj(Double.parseDouble(parzj[i]));
						map.put("parcj" + i, String.format("%.2f", ms.streams.ion(EIon.values()[i]).parcj()));
						map.put("parmj" + i, String.format("%.2f", ms.streams.ion(EIon.values()[i]).parmj() * 1000));
						map.put("parzj" + i, String.format("%.2f", ms.streams.ion(EIon.values()[i]).parzj()));
					}
					for (int i = 0; i < EIon.values().length - 11; i++) {
						ms.streams.ion(EIon.values()[i + 11]).parcj(Double.parseDouble(right_parcj[i]));
						ms.streams.ion(EIon.values()[i + 11]).parmj(Double.parseDouble(right_parmj[i]) / 1000);
						ms.streams.ion(EIon.values()[i + 11]).parzj(Double.parseDouble(right_parzj[i]));
						map.put("right_parcj" + i,
								String.format("%.2f", ms.streams.ion(EIon.values()[i + 11]).parcj()));
						map.put("right_parmj" + i,
								String.format("%.2f", ms.streams.ion(EIon.values()[i + 11]).parmj() * 1000));
						map.put("right_parzj" + i,
								String.format("%.2f", ms.streams.ion(EIon.values()[i + 11]).parzj()));
					}
					ms.streams.ion(EIon.values()[14]).parcj(Double.parseDouble(right_parcj[3]));
					ms.streams.ion(EIon.values()[14]).parmj(Double.parseDouble(right_parmj[3]) / 1000);
					ms.streams.ion(EIon.values()[14]).parzj(Double.parseDouble(right_parzj[3]));
					map.put("right_parcj" + 3, String.format("%.2f", ms.streams.ion(EIon.values()[14]).parcj()));
					map.put("right_parmj" + 3, String.format("%.2f", ms.streams.ion(EIon.values()[14]).parmj() * 1000));
					map.put("right_parzj" + 3, String.format("%.2f", ms.streams.ion(EIon.values()[14]).parzj()));
					map.put("parcjd", String.format("%.1f", ms.streams.parcjd()));// 碱度
				}
				map.put("parS", String.format("%.2f", ms.streams.parS()));// 电导率
				map.put("paru", String.format("%.3f", ms.streams.paru()));// 离子强度
				map.put("parpif", String.format("%.2f", ms.streams.parpif()));// 渗透压
			} else if (key.equals("org")) {
				// 有机物信息
				int index = Integer.parseInt(request.getParameter("org"));
				map.put("index", index);
				if (index == 1) {
					ms.streams.codmode(true);
					ms.streams.cods()[0].parcj = Double.parseDouble(request.getParameter("cod_C1"));
					map.put("cod_C1", String.format("%.2f", ms.streams.cods()[0].parcj));
				} else if (index == 2) {
					ms.streams.codmode(false);
					for (int j = 0; j < ms.streams.cods().length; j++) {
						ms.streams.cods()[j].parMj = Double.parseDouble(request.getParameter("org_M" + (j + 1)));
						ms.streams.cods()[j].parcj = Double.parseDouble(request.getParameter("org_C" + (j + 1)));
						map.put("org_M" + (j + 1), String.format("%.2f", ms.streams.cods()[j].parMj));
						map.put("org_C" + (j + 1), String.format("%.2f", ms.streams.cods()[j].parcj));
					}
				}
			} else if (key.equals("system")) {
				// 系统设计参数
				ms.section(Integer.parseInt(request.getParameter("design_sections")));
				map.put("sections", String.format("%d", ms.section()));
				for (int i = 0; i < ms.section(); i++) {
					map.put("model" + i, ms.sections()[i].model);
					map.put("parEi" + i, String.format("%d", ms.sections()[i].parEi));
					map.put("parNVi" + i, String.format("%d", ms.sections()[i].parNVi));
					map.put("parPpi" + i, String.format("%.2f", ms.sections()[i].parPpi));
					map.put("parDpi" + i, String.format("%.2f", ms.sections()[i].parDpi));
					map.put("parPLi" + i, String.format("%.2f", ms.sections()[i].parPLi));
				}
				// 水量信息
				ms.pariQp = Double.parseDouble(request.getParameter("design_Qp"));
				ms.pariY = Double.parseDouble(request.getParameter("design_pariY"));
				ms.pariQr = Double.parseDouble(request.getParameter("design_pariQr"));
				map.put("pariQp", String.format("%.2f", ms.pariQp));
				map.put("pariY", String.format("%.2f", ms.pariY));
				map.put("pariQr", String.format("%.2f", ms.pariQr));
				map.put("pariJ", String.format("%.2f", ms.pariJ()));
				map.put("pariQc", String.format("%.2f", ms.pariQc()));
				map.put("pariQf", String.format("%.2f", ms.pariQf()));
				map.put("pariYD", String.format("%.2f", ms.pariYD()));
			} else if (key.equals("sections")) {
				// 系统设计参数
				ms.section(Integer.parseInt(request.getParameter("design_sections")));
				map.put("sections", String.format("%d", ms.section()));
				for (int i = 0; i < ms.section(); i++) {
					map.put("model" + i, ms.sections()[i].model);
					map.put("parEi" + i, String.format("%d", ms.sections()[i].parEi));
					map.put("parNVi" + i, String.format("%d", ms.sections()[i].parNVi));
					map.put("parPpi" + i, String.format("%.2f", ms.sections()[i].parPpi));
					map.put("parDpi" + i, String.format("%.2f", ms.sections()[i].parDpi));
					map.put("parPLi" + i, String.format("%.2f", ms.sections()[i].parPLi));
				}
				// 水量信息
				ms.pariQp = Double.parseDouble(request.getParameter("design_Qp"));
				ms.pariY = Double.parseDouble(request.getParameter("design_pariY"));
				ms.pariQr = Double.parseDouble(request.getParameter("design_pariQr"));
				map.put("pariJ", String.format("%.2f", ms.pariJ()));
			} else if (key.equals("table")) {
				ms.section(Integer.parseInt(request.getParameter("design_sections")));
				for (int i = 0; i < ms.section(); i++) {
					ms.sections()[i].model = model[i];
					ms.sections()[i].parEi = Integer.parseInt(parEi_str[i]);
					ms.sections()[i].parNVi = Integer.parseInt(parNVi_str[i]);
					ms.sections()[i].parPpi = Double.parseDouble(parPpi_str[i]);
					ms.sections()[i].parDpi = Double.parseDouble(parDpi_str[i]);
					ms.sections()[i].parPLi = Double.parseDouble(parPLi_str[i]);
					map.put("model" + i, ms.sections()[i].model);
					map.put("parEi" + i, String.format("%d", ms.sections()[i].parEi));
					map.put("parNVi" + i, String.format("%d", ms.sections()[i].parNVi));
					map.put("parPpi" + i, String.format("%.2f", ms.sections()[i].parPpi));
					map.put("parDpi" + i, String.format("%.2f", ms.sections()[i].parDpi));
					map.put("parPLi" + i, String.format("%.2f", ms.sections()[i].parPLi));
				}
				ms.pariQp = Double.parseDouble(request.getParameter("design_Qp"));
				ms.pariY = Double.parseDouble(request.getParameter("design_pariY"));
				ms.pariQr = Double.parseDouble(request.getParameter("design_pariQr"));
				map.put("pariQp", String.format("%.2f", ms.pariQp));
				map.put("pariY", String.format("%.2f", ms.pariY));
				map.put("pariQr", String.format("%.2f", ms.pariQr));
				map.put("pariJ", String.format("%.2f", ms.pariJ()));
				map.put("pariQc", String.format("%.2f", ms.pariQc()));
				map.put("pariQf", String.format("%.2f", ms.pariQf()));
				map.put("pariYD", String.format("%.2f", ms.pariYD()));
			} else if (key.equals("iontable")) {
				int cols = Integer.parseInt(request.getParameter("state"));
				for (int i = 0; i < 11; i++) {
					if (cols == 2 || cols == 1 || cols > 4) {
						ms.streams.ion(EIon.values()[i]).parcj(Double.parseDouble(parcj[i]));
					} else if (cols == 3) {
						ms.streams.ion(EIon.values()[i]).parmj(Double.parseDouble(parmj[i]) / 1000);
					} else if (cols == 4) {
						ms.streams.ion(EIon.values()[i]).parzj(Double.parseDouble(parzj[i]));
					}
					map.put("parcj" + i, String.format("%.2f", ms.streams.ion(EIon.values()[i]).parcj()));
					map.put("parmj" + i, String.format("%.2f", ms.streams.ion(EIon.values()[i]).parmj() * 1000));
					map.put("parzj" + i, String.format("%.2f", ms.streams.ion(EIon.values()[i]).parzj()));
				}
				for (int i = 0; i < EIon.values().length - 11; i++) {
					if (cols == 6 || cols < 6) {
						ms.streams.ion(EIon.values()[i + 11]).parcj(Double.parseDouble(right_parcj[i]));
					} else if (cols == 7) {
						ms.streams.ion(EIon.values()[i + 11]).parmj(Double.parseDouble(right_parmj[i]) / 1000);
					} else if (cols == 8) {
						ms.streams.ion(EIon.values()[i + 11]).parzj(Double.parseDouble(right_parzj[i]));
					}
					map.put("right_parcj" + i, String.format("%.2f", ms.streams.ion(EIon.values()[i + 11]).parcj()));
					map.put("right_parmj" + i,
							String.format("%.2f", ms.streams.ion(EIon.values()[i + 11]).parmj() * 1000));
					map.put("right_parzj" + i, String.format("%.2f", ms.streams.ion(EIon.values()[i + 11]).parzj()));
				}
				ms.streams.parT(Double.parseDouble(request.getParameter("water_tem")));
				ms.streams.parpH(Double.parseDouble(request.getParameter("water_ph")));
				map.put("parT", String.format("%.1f", ms.streams.parT()));// 温度
				map.put("parpH", String.format("%.2f", ms.streams.parpH()));// pH
				map.put("Eionlength", EIon.values().length);
				map.put("tds", String.format("%.1f", ms.streams.tds()));// tds
				map.put("parS", String.format("%.2f", ms.streams.parS()));// 电导率
				map.put("paru", String.format("%.3f", ms.streams.paru()));// 离子强度
				map.put("parcjd", String.format("%.1f", ms.streams.parcjd()));// 碱度
				map.put("parpif", String.format("%.2f", ms.streams.parpif()));// 渗透压
				map.put("parqC", String.format("%.2f", ms.streams.parqC()));
				map.put("parqA", String.format("%.2f", ms.streams.parqA()));
				map.put("parSCaCO3", String.format("%.2f", ms.streams.parSCaCO3()));
				map.put("parSBaSO4", String.format("%.2f", ms.streams.parSBaSO4() * 100));
				map.put("parSCaSO4", String.format("%.2f", ms.streams.parSCaSO4() * 100));
				map.put("parSSrSO4", String.format("%.2f", ms.streams.parSSrSO4() * 100));
				map.put("parSCa3PO42", String.format("%.2f", ms.streams.parSCa3PO42() * 100));
				map.put("parSCaF2", String.format("%.2f", ms.streams.parSCaF2() * 100));
			}

		} catch (NumberFormatException e) {
			map.put("success", false);
			map.put("errorMsg", e.getMessage());
		} catch (Exception e) {
			map.put("success", false);
			map.put("errorMsg", e.getMessage());
		}
		String jsonstr = gson.toJson(map);
		PrintWriter pw = response.getWriter();
		pw.write(jsonstr);
		pw.flush();
		pw.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
