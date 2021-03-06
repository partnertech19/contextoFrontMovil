package com.app.partner.clinica.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.app.partner.clinica.R;
import com.app.partner.clinica.adapters.RecyclerAdapterPaciente;
import com.app.partner.clinica.code.RangeTimePickerDialog;
import com.app.partner.clinica.common.Constantes;
import com.app.partner.clinica.common.SharedPreferencesManager;
import com.app.partner.clinica.models.request.Consultorio;
import com.app.partner.clinica.models.request.Consultoriodoctor;
import com.app.partner.clinica.models.request.Empleado;
import com.app.partner.clinica.models.request.Pacientes;
import com.app.partner.clinica.models.request.Terapiaindividual;
import com.app.partner.clinica.models.request.Tiposesion;
import com.app.partner.clinica.models.response.ResponseConsultorio;
import com.app.partner.clinica.models.response.ResponseConsultoriodoctor;
import com.app.partner.clinica.models.response.ResponsePaciente;
import com.app.partner.clinica.models.response.ResponseTerapiaEntrevista;
import com.app.partner.clinica.models.response.ResponseTiposesion;
import com.app.partner.clinica.services.instance.IConsultorio;
import com.app.partner.clinica.services.instance.IPaciente;
import com.app.partner.clinica.services.instance.ITerapiaIndividual;
import com.app.partner.clinica.services.instance.ITiposesion;
import com.app.partner.clinica.services.service.ConsultorioService;
import com.app.partner.clinica.services.service.PacienteService;
import com.app.partner.clinica.services.service.TerapiaIndividualService;
import com.app.partner.clinica.services.service.TiposesionService;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CitaActivity extends AppCompatActivity {

    private Empleado empleado;

    private EditText edtPacienteNombre, edtCitaFecha, edtCitaHora, edtCitaNotas, edtCitaDuracion;
    private Button btnNuevaCita;
    private Spinner spnnTipoSesion, spnnConsultorio, spnnHorarioConsul;

    private SearchView srcPaciente;
    private RecyclerView rcyPaciente;
    private RecyclerAdapterPaciente adtPaciente;
    private RecyclerView.LayoutManager lymPaciente;

    private Calendar fecha;

    private IPaciente iPaciente;
    private PacienteService sPaciente;
    private ITerapiaIndividual iTerapiaIndividual;
    private TerapiaIndividualService sterapiaIndividual;
    private ITiposesion iTiposesion;
    private TiposesionService sTiposesion;
    private IConsultorio iConsultorio;
    private ConsultorioService sConsultorio;
    private Consultorio consultorio;

    private Pacientes pacientes;
    private Terapiaindividual terapiaindividual;
    private Consultoriodoctor consultoriodoctor;

    private Integer accion;
    InputFilter timeFilter;

    private List<Tiposesion> lsTiposesion;
    private List<Consultorio> lsConsultorio;
    private List<Consultoriodoctor> lsConsultoriodoctor;

    private int minHour = -1, minMinute = -1, maxHour = 100, maxMinute = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cita);
        empleado = SharedPreferencesManager.getPrefEmpleado();

        retrofitInit();

        Bundle extras = getIntent().getExtras();
        terapiaindividual = (Terapiaindividual) extras.getSerializable("terapiaindividual");

        agregarFiltro();
        Calendar calendar = Calendar.getInstance();

        if (terapiaindividual == null) {
            terapiaindividual = new Terapiaindividual();
            accion = 1;
            calendar.setTimeInMillis(extras.getLong("fecha"));
            this.fecha = calendar;
            listarPacientes();
        } else {
            accion = extras.getInt("accion");
            calendar.setTimeInMillis(terapiaindividual.getTfechaterapia());
            this.fecha = calendar;
            pacientes = terapiaindividual.getPacientes();
        }
        obtenerViews();
        eventosViews();
    }

    private void listarPacientes() {
        Call<ResponsePaciente> call = sPaciente.listarPorDoctor(empleado.getIidreferencia());
        call.enqueue(new Callback<ResponsePaciente>() {
            @Override
            public void onResponse(Call<ResponsePaciente> call, Response<ResponsePaciente> response) {
                if (response.isSuccessful()) {
                    crearRecycler(response.body().getAaData());
                } else {
                    Constantes.alertWarning(Constantes.INFORMACION, "No se logró listar los pacientes");
                }
            }

            @Override
            public void onFailure(Call<ResponsePaciente> call, Throwable t) {
                Constantes.alertError(Constantes.PROBLEMA, "COMPRUEBE QUE TENGA CONEXIÓN A INTERNET");
            }
        });
    }

    private void obtenerViews() {
        srcPaciente = findViewById(R.id.srcPaciente);
        rcyPaciente = findViewById(R.id.rcyPaciente);
        edtPacienteNombre = findViewById(R.id.edtPacienteNombre);
        edtCitaFecha = findViewById(R.id.edtCitaFecha);
        edtCitaHora = findViewById(R.id.edtCitaHora);
        edtCitaNotas = findViewById(R.id.edtCitaNotas);
        btnNuevaCita = findViewById(R.id.btnNuevaCita);
        spnnConsultorio = findViewById(R.id.spnnConsultorio);
        spnnHorarioConsul = findViewById(R.id.spnnHorarioConsul);
        spnnTipoSesion = findViewById(R.id.spnnTipoSesion);
        edtCitaDuracion = findViewById(R.id.edtCitaDuracion);
        edtCitaDuracion.setFilters(new InputFilter[]{timeFilter});

        if (accion == 1) {
            edtCitaFecha.setText(fecha.get(Calendar.YEAR) + "-" + retornarCero(fecha.get(Calendar.MONTH) + 1) + "-" + retornarCero(fecha.get(Calendar.DAY_OF_MONTH)));
            edtCitaFecha.setEnabled(false);
            srcPaciente.setVisibility(View.VISIBLE);
            rcyPaciente.setVisibility(View.VISIBLE);
        } else {
            edtPacienteNombre.setText(pacientes.getSnombre());
            edtCitaFecha.setText(fecha.get(Calendar.YEAR) + "-" + retornarCero(fecha.get(Calendar.MONTH) + 1) + "-" + retornarCero(fecha.get(Calendar.DAY_OF_MONTH)));
            int hora = fecha.get(Calendar.HOUR_OF_DAY);
            int minuto = fecha.get(Calendar.MINUTE);
            edtCitaHora.setText(retornarCero(hora) + ":" + retornarCero(minuto));
            edtCitaDuracion.setText(retornarTiempo(terapiaindividual.getIduracionmin()));
            edtCitaNotas.setText(terapiaindividual.getSnotas());
            if (accion == 2) {
                edtCitaNotas.setEnabled(false);
            } else if (accion == 3) {
                edtCitaFecha.setEnabled(false);
                edtCitaHora.setEnabled(false);
                edtCitaDuracion.setEnabled(false);
                spnnConsultorio.setEnabled(false);
                spnnHorarioConsul.setEnabled(false);
                spnnTipoSesion.setEnabled(false);
            }
        }
    }

    private void eventosViews() {
        srcPaciente.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adtPaciente.filter(s);
                return false;
            }
        });

        edtCitaFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePicker = new DatePickerDialog(CitaActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int anno, int mes, int dia) {
                        fecha.set(anno, mes, dia);
                        edtCitaFecha.setText(anno + "-" + retornarCero(mes + 1) + "-" + retornarCero(dia));
                        listarConsulDoctor(consultorio);
                    }
                }, fecha.get(Calendar.YEAR), fecha.get(Calendar.MONTH), fecha.get(Calendar.DAY_OF_MONTH));
                datePicker.show();
            }
        });

        edtCitaHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RangeTimePickerDialog rangeTimePickerDialog = new RangeTimePickerDialog(CitaActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hora, int minuto) {
                        fecha.set(Calendar.HOUR_OF_DAY, hora);
                        fecha.set(Calendar.MINUTE, minuto);
                        fecha.set(Calendar.SECOND, 1);
                        edtCitaHora.setText(retornarCero(hora) + ":" + retornarCero(minuto));
//                        edtCitaHora.setText(retornarCero((hora > 12 ? hora - 12 : (hora == 0) ? 12 : hora)) + ":" + retornarCero(minuto) + " " + ((hora >= 12 && hora != 0) ? "pm" : "am"));
                    }
                }, fecha.get(Calendar.HOUR_OF_DAY), fecha.get(Calendar.MINUTE), true);

                rangeTimePickerDialog.setMin(minHour, minMinute);
                rangeTimePickerDialog.setMax(maxHour, maxMinute);
                rangeTimePickerDialog.show();
            }
        });

        btnNuevaCita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean tiempoExcedido = !edtCitaDuracion.getText().toString().isEmpty() ? tiempoPermitido() : false;
                if (pacientes != null && !edtCitaHora.getText().toString().isEmpty() && tiempoExcedido) {
                    new AlertDialog.Builder(CitaActivity.this)
                            .setCancelable(false)
                            .setTitle("Confirmar")
                            .setMessage(accion == 1 ? "¿Desea registrar la terapia?" : accion == 2 ? "¿Desea reprogramador la terapia?" : "¿Desea marcar como completa la terapia?")
                            .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int i) {
                                    dialog.dismiss();
                                }
                            })
                            .setPositiveButton(accion == 1 ? "Registrar" : accion == 2 ? "Reprogramar" : "Completar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int i) {
                                    if (accion == 1) {
                                        registrarTerapia();
                                    } else if (accion == 2) {
                                        reprogramarTerapia();
                                    } else {
                                        completarTerapia();
                                    }
                                    dialog.dismiss();
                                }
                            })
                            .show();
                } else {
                    Constantes.alertWarning(Constantes.VALIDACION, "Complete los campos");
                    if (edtPacienteNombre.getText().toString().isEmpty())
                        edtPacienteNombre.setError("Campo requerido");
                    if (edtCitaHora.getText().toString().isEmpty())
                        edtCitaHora.setError("Campo requerido");
                    if (edtCitaDuracion.getText().toString().isEmpty())
                        edtCitaDuracion.setError("Campo requerido");
                    if (!tiempoExcedido) edtCitaDuracion.setError("Tiempo excedido");
                }
            }
        });

        listarConsultorio();
        listarTiposesion();
    }

    private boolean tiempoPermitido() {
        int contador = 0;
        Calendar fTerapia = Calendar.getInstance();
        fTerapia.setTimeInMillis(fecha.getTimeInMillis());

        Calendar fConsulDoctor = Calendar.getInstance();
        fConsulDoctor.setTimeInMillis(consultoriodoctor.getTfechafin());

        while (fTerapia.before(fConsulDoctor)) {
            fTerapia.add(Calendar.MINUTE, 1);
            contador++;
        }
        int minuto = retornarMinutos(edtCitaDuracion.getText().toString());

        return minuto <= contador;
    }

    private void listarConsultorio() {
        Call<ResponseConsultorio> call = sConsultorio.listarPorDoctor(empleado.getIidreferencia());
        call.enqueue(new Callback<ResponseConsultorio>() {
            @Override
            public void onResponse(Call<ResponseConsultorio> call, Response<ResponseConsultorio> response) {
                if (response.isSuccessful()) {

                    lsConsultorio = response.body().getAaData();
                    if (lsConsultorio != null && lsConsultorio.size() > 0) {
                        for (int i = 0; i < lsConsultorio.size(); i++) {
                            Consultorio cs = lsConsultorio.get(i);
                            if (cs.getIidconsultorio() == terapiaindividual.getIidconsultorio()) {
                                lsConsultorio.remove(i);
                                lsConsultorio.add(0, cs);
                                break;
                            }
                        }

                        ArrayAdapter adapter = new ArrayAdapter(CitaActivity.this, android.R.layout.simple_spinner_dropdown_item, lsConsultorio);
                        spnnConsultorio.setAdapter(adapter);
                        spnnConsultorio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                consultorio = lsConsultorio.get(i);
                                terapiaindividual.setIidconsultorio(consultorio.getIidconsultorio());
                                listarConsulDoctor(consultorio);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                    } else {
                        Constantes.alertWarning(Constantes.INFORMACION, "No se logró listar los consultorios");
                    }

                } else {
                    Constantes.alertWarning(Constantes.INFORMACION, "No se logró listar los consultorios");
                }
            }

            @Override
            public void onFailure(Call<ResponseConsultorio> call, Throwable t) {
                Constantes.alertError(Constantes.PROBLEMA, "COMPRUEBE QUE TENGA CONEXIÓN A INTERNET");
            }
        });
    }

    private void listarConsulDoctor(Consultorio consultorio) {
        Consultoriodoctor cd = new Consultoriodoctor();
        cd.setIidconsultorio(consultorio.getIidconsultorio());
        cd.setTfechainicio(fecha.getTime().getTime());
        Call<ResponseConsultoriodoctor> call = sConsultorio.listarConsulDoctorPorFecha(cd);
        call.enqueue(new Callback<ResponseConsultoriodoctor>() {
            @Override
            public void onResponse(Call<ResponseConsultoriodoctor> call, Response<ResponseConsultoriodoctor> response) {
                if (response.isSuccessful()) {

                    List<Consultoriodoctor> lsConsulDoctor = response.body().getAaData();
                    if (lsConsulDoctor != null && lsConsulDoctor.size() > 0) {

                        ArrayAdapter adapter = new ArrayAdapter(CitaActivity.this, android.R.layout.simple_spinner_dropdown_item, lsConsulDoctor);
                        spnnHorarioConsul.setAdapter(adapter);
                        spnnHorarioConsul.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                consultoriodoctor = lsConsulDoctor.get(i);
                                Calendar finicioHorario = Calendar.getInstance();
                                finicioHorario.setTimeInMillis(consultoriodoctor.getTfechainicio());
                                minHour = finicioHorario.get(Calendar.HOUR_OF_DAY);
                                minMinute = finicioHorario.get(Calendar.MINUTE);

                                Calendar ffinHorario = Calendar.getInstance();
                                ffinHorario.setTimeInMillis(consultoriodoctor.getTfechafin());
                                maxHour = ffinHorario.get(Calendar.HOUR_OF_DAY);
                                maxMinute = ffinHorario.get(Calendar.MINUTE);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                    } else {
                        ArrayAdapter adapter = new ArrayAdapter(CitaActivity.this, android.R.layout.simple_spinner_dropdown_item, new ArrayList());
                        spnnHorarioConsul.setAdapter(adapter);
                        edtCitaHora.setText("");
                        Constantes.alertInfo(Constantes.INFORMACION, "No hay horarios disponibles en esta fecha");
                    }
                } else {
                    Constantes.alertWarning(Constantes.INFORMACION, "No se logró listar los horarios");
                }
            }

            @Override
            public void onFailure(Call<ResponseConsultoriodoctor> call, Throwable t) {
                Constantes.alertError(Constantes.PROBLEMA, "COMPRUEBE QUE TENGA CONEXIÓN A INTERNET");
            }
        });

    }

    private void listarTiposesion() {
        Call<ResponseTiposesion> call = sTiposesion.listar();
        call.enqueue(new Callback<ResponseTiposesion>() {
            @Override
            public void onResponse(Call<ResponseTiposesion> call, Response<ResponseTiposesion> response) {
                if (response.isSuccessful()) {

                    lsTiposesion = response.body().getAaData();
                    for (int i = 0; i < lsTiposesion.size(); i++) {
                        Tiposesion ts = lsTiposesion.get(i);
                        if (ts.getIidtiposesion() == terapiaindividual.getIidtiposesion()) {
                            lsTiposesion.remove(i);
                            lsTiposesion.add(0, ts);
                            break;
                        }
                    }

                    ArrayAdapter adapter = new ArrayAdapter(CitaActivity.this, android.R.layout.simple_spinner_dropdown_item, lsTiposesion);
                    spnnTipoSesion.setAdapter(adapter);
                    spnnTipoSesion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            terapiaindividual.setIidtiposesion(lsTiposesion.get(i).getIidtiposesion());
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                } else {
                    Constantes.alertWarning(Constantes.INFORMACION, "No se logró listar los tipos de sesión");
                }
            }

            @Override
            public void onFailure(Call<ResponseTiposesion> call, Throwable t) {
                Constantes.alertError(Constantes.PROBLEMA, "COMPRUEBE QUE TENGA CONEXIÓN A INTERNET");
            }
        });
    }

    private void completarTerapia() {
        this.terapiaindividual.setSnotas(edtCitaNotas.getText().toString());
        this.terapiaindividual.setIestado(2);
        this.terapiaindividual.setPacientes(null);

        Call<ResponseTerapiaEntrevista> call = sterapiaIndividual.actualizar(this.terapiaindividual);
        call.enqueue(new Callback<ResponseTerapiaEntrevista>() {
            @Override
            public void onResponse(Call<ResponseTerapiaEntrevista> call, Response<ResponseTerapiaEntrevista> response) {
                if (response.isSuccessful()) {
//                    ResponseTerapiaIndividual resp = response.body();
                    Constantes.alertSuccess(Constantes.NOTIFICACION, "Registro completado correctamente");

                    Intent main = new Intent(CitaActivity.this, MainActivity.class);
                    startActivity(main);
                    finish();
                } else {
                    Constantes.alertWarning(Constantes.NOTIFICACION, "Error al completar registro");
                }
            }

            @Override
            public void onFailure(Call<ResponseTerapiaEntrevista> call, Throwable t) {
                Constantes.alertError(Constantes.PROBLEMA, "COMPRUEBE QUE TENGA CONEXIÓN A INTERNET");
            }
        });
    }

    private void reprogramarTerapia() {
        this.terapiaindividual.setTfechaterapia(fecha.getTime().getTime());
        this.terapiaindividual.setIduracionmin(retornarMinutos(edtCitaDuracion.getText().toString()));
        this.terapiaindividual.setPacientes(null);
        Call<ResponseTerapiaEntrevista> call = sterapiaIndividual.actualizar(this.terapiaindividual);
        call.enqueue(new Callback<ResponseTerapiaEntrevista>() {
            @Override
            public void onResponse(Call<ResponseTerapiaEntrevista> call, Response<ResponseTerapiaEntrevista> response) {
                if (response.isSuccessful()) {
//                    ResponseTerapiaIndividual resp = response.body();
                    Constantes.alertSuccess(Constantes.NOTIFICACION, "Registro reprogramado correctamente");

                    Intent main = new Intent(CitaActivity.this, MainActivity.class);
                    startActivity(main);
                    finish();
                } else {
                    Constantes.alertWarning(Constantes.NOTIFICACION, "Error al reprogramar registro");
                }
            }

            @Override
            public void onFailure(Call<ResponseTerapiaEntrevista> call, Throwable t) {
                Constantes.alertError(Constantes.PROBLEMA, "COMPRUEBE QUE TENGA CONEXIÓN A INTERNET");
            }
        });
    }

    private void registrarTerapia() {
//        Terapiaindividual terapiaindividual = new Terapiaindividual();
        this.terapiaindividual.setIiddoctor(empleado.getIidreferencia());
        this.terapiaindividual.setIidpaciente(pacientes.getIidpaciente());
        this.terapiaindividual.setTfechaterapia(fecha.getTime().getTime());
        this.terapiaindividual.setSnotas(edtCitaNotas.getText().toString());
        this.terapiaindividual.setIidempresa(empleado.getIdempresa());
        this.terapiaindividual.setIduracionmin(retornarMinutos(edtCitaDuracion.getText().toString()));

        Call<ResponseTerapiaEntrevista> call = sterapiaIndividual.insertar(terapiaindividual);
        call.enqueue(new Callback<ResponseTerapiaEntrevista>() {
            @Override
            public void onResponse(Call<ResponseTerapiaEntrevista> call, Response<ResponseTerapiaEntrevista> response) {
                if (response.isSuccessful()) {
                    ResponseTerapiaEntrevista resp = response.body();
                    if (resp.getOk()) {
                        Constantes.alertSuccess(Constantes.NOTIFICACION, "Terapia registrada correctamente");
                        Intent main = new Intent(CitaActivity.this, MainActivity.class);
                        startActivity(main);
                        finish();
                    } else {

                        new AlertDialog.Builder(CitaActivity.this)
                                .setCancelable(false)
                                .setTitle(Constantes.INFORMACION)
                                .setMessage(resp.getMsg())
                                .setNegativeButton("Cerrar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int i) {
                                        dialog.dismiss();
                                    }
                                })
                                .show();
//                        Constantes.alertInfo(Constantes.NOTIFICACION, resp.getMsg());
                    }
                } else {
                    Constantes.alertWarning(Constantes.NOTIFICACION, "Error al registrar la terapia");
                }
            }

            @Override
            public void onFailure(Call<ResponseTerapiaEntrevista> call, Throwable t) {
                Constantes.alertError(Constantes.PROBLEMA, "COMPRUEBE QUE TENGA CONEXIÓN A INTERNET");
            }
        });
    }

    private int retornarMinutos(String text) {
        String[] tiempo = text.split(":");
        int hora = Integer.parseInt(tiempo[0]);
        int minuto = Integer.parseInt(tiempo[1]);
        return ((hora * 60) + minuto);
    }

    private String retornarTiempo(int minuto) {
        int h = minuto / 60;
        int m = minuto % 60;
        return (h < 10 ? "0" + h : "" + h) + ":" + (m < 10 ? "0" + m : "" + m);
    }

    private String retornarCero(int numero) {
        return (numero < 10 ? "0" + numero : "" + numero);
    }

    private void crearRecycler(List<Pacientes> lsPaciente) {
        if (lsPaciente != null && lsPaciente.size() > 0) {
            lymPaciente = new LinearLayoutManager(CitaActivity.this);
            adtPaciente = new RecyclerAdapterPaciente(lsPaciente, new RecyclerAdapterPaciente.OnItemClickListener() {
                @Override
                public void onCheckClick(Pacientes paciente, int position) {
                    edtPacienteNombre.setText(paciente.getSnombre());
                    pacientes = paciente;
                }
            });
            rcyPaciente.setLayoutManager(lymPaciente);
            rcyPaciente.setAdapter(adtPaciente);
        } else {
            Constantes.alertWarning(Constantes.INFORMACION, "No hay pacientes asociados");
        }
    }

    private void agregarFiltro() {
        timeFilter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

                if (source.length() == 0) {
                    return null;// deleting, keep original editing
                }
                String result = "";
                result += dest.toString().substring(0, dstart);
                result += source.toString().substring(start, end);
                result += dest.toString().substring(dend, dest.length());

                if (result.length() > 5) {
                    return "";// do not allow this edit
                }

                boolean allowEdit = true;
                char c;
                if (result.length() == 1) {
                    c = result.charAt(0);
                    if (c == '0' || c == '1' || c == '2')
                        allowEdit &= (c >= '0' && c <= '2');
                    else {
                        return "0" + source + ":";
                    }
                } else if (result.length() == 2) {
                    c = result.charAt(1);
                    if (c == ':') {
                        edtCitaDuracion.setText("0" + result.charAt(0) + ":");
                        allowEdit = true;
                    } else if (result.charAt(0) == '0' || result.charAt(0) == '1') {
                        return source + ":";
                    } else if (result.charAt(0) == '2' && result.charAt(1) <= '3') {
                        return source + ":";
                    }
                    allowEdit &= false;
                } else if (result.length() == 3) {
                    c = result.charAt(2);
                    allowEdit &= (c == ':');
                } else if (result.length() == 4) {
                    c = result.charAt(3);
                    allowEdit &= (c >= '0' && c <= '5');
                } else if (result.length() == 5) {
                    c = result.charAt(4);
                    allowEdit &= (c >= '0' && c <= '9');
                }
                return allowEdit ? null : "";
            }

        };
    }

    private void retrofitInit() {
        iPaciente = IPaciente.getInstance();
        sPaciente = iPaciente.getService();

        iTerapiaIndividual = ITerapiaIndividual.getInstance();
        sterapiaIndividual = iTerapiaIndividual.getService();

        iTiposesion = ITiposesion.getInstance();
        sTiposesion = iTiposesion.getService();

        iConsultorio = IConsultorio.getInstance();
        sConsultorio = iConsultorio.getService();
    }

}