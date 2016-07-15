package com.example.luiz.sanmarcosavenue.Activities;

import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.luiz.sanmarcosavenue.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import java.math.BigDecimal;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final float DEFAULT_ZOOM = (float) 15.5;
    private GoogleMap mMap;
    LatLng unmsm = new LatLng(-12.056131, -77.084369);
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {

        mMap = googleMap;
        mMap.moveCamera(CameraUpdateFactory.newLatLng(unmsm));
        //googleMap.getUiSettings().setScrollGesturesEnabled(false);
        mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition position) {
                checkBounds();
            }
        });
        /*mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition position) {
                BigDecimal bigDecimal = new BigDecimal(Double.toString(Math.pow((position.target.latitude + 12.056131), 2) + Math.pow((position.target.longitude + 77.084369), 2)));
                if (position.zoom < DEFAULT_ZOOM) {
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(unmsm, DEFAULT_ZOOM));
                }
                if (position.zoom > 18) {
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(18));
                }
                if(bigDecimal.doubleValue() > 0.0000395){
                    googleMap.getUiSettings().setScrollGesturesEnabled(false);
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng((position.target.latitude - 12.056131) / 2, (position.target.longitude - 77.084369) / 2)));
                    //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(unmsm, DEFAULT_ZOOM));
                    //Toast.makeText(getApplicationContext(), String.valueOf(bigDecimal), Toast.LENGTH_SHORT).show();
                    //mMap.addMarker(new MarkerOptions().position(new LatLng(position.target.latitude, position.target.longitude)));
                }
                else
                    googleMap.getUiSettings().setScrollGesturesEnabled(true);

            }});*/
        //Facultad de Ciencias Físicas
        Polygon Fcf1 = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-12.059572, -77.081905), new LatLng(-12.059531, -77.081737),
                        new LatLng(-12.059572, -77.081723), new LatLng(-12.059566, -77.081678),
                        new LatLng(-12.060100, -77.081541), new LatLng(-12.060191, -77.081505),
                        new LatLng(-12.060210, -77.081550), new LatLng(-12.060297, -77.081522),
                        new LatLng(-12.060303, -77.081597), new LatLng(-12.060275, -77.081648),
                        new LatLng(-12.060212, -77.081684), new LatLng(-12.060166, -77.081695),
                        new LatLng(-12.060171, -77.081712), new LatLng(-12.059775, -77.081804),
                        new LatLng(-12.059775, -77.081818))
                .strokeColor(Color.GREEN)
                .fillColor(Color.GREEN));
        Polygon Fcf2 = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-12.055913, -77.081720), new LatLng(-12.056061, -77.081469),
                        new LatLng(-12.056149, -77.081728), new LatLng(-12.056180, -77.081866),
                        new LatLng(-12.056135, -77.081880), new LatLng(-12.056152, -77.081976),
                        new LatLng(-12.056204, -77.081967), new LatLng(-12.056246, -77.082125),
                        new LatLng(-12.056203, -77.082138), new LatLng(-12.056216, -77.082195),
                        new LatLng(-12.056057, -77.082229))
                .strokeColor(Color.GREEN)
                .fillColor(Color.GREEN));
        //Facultad de Ciencias Biológicas
        Polygon Fcb1 = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-12.059781, -77.082552), new LatLng(-12.059619, -77.081901),
                        new LatLng(-12.059786, -77.081858), new LatLng(-12.059861, -77.082145),
                        new LatLng(-12.059900, -77.082134), new LatLng(-12.059841, -77.081892),
                        new LatLng(-12.059913, -77.081878), new LatLng(-12.060019, -77.082328),
                        new LatLng(-12.059958, -77.082342), new LatLng(-12.059905, -77.082169),
                        new LatLng(-12.059869, -77.082180), new LatLng(-12.059949, -77.082509))
                .strokeColor(Color.GREEN)
                .fillColor(Color.GREEN));
        Polygon Fcb2 = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-12.059215, -77.082091), new LatLng(-12.059278, -77.082074),
                        new LatLng(-12.059271, -77.082051), new LatLng(-12.059226, -77.082059),
                        new LatLng(-12.059196, -77.081968), new LatLng(-12.059181, -77.081963),
                        new LatLng(-12.059173, -77.081935), new LatLng(-12.059178, -77.081916),
                        new LatLng(-12.059156, -77.081826), new LatLng(-12.059377, -77.081766),
                        new LatLng(-12.059404, -77.081865), new LatLng(-12.059436, -77.081962),
                        new LatLng(-12.059408, -77.081977), new LatLng(-12.059423, -77.082039),
                        new LatLng(-12.059478, -77.082027), new LatLng(-12.059498, -77.082105),
                        new LatLng(-12.059469, -77.082115), new LatLng(-12.059492, -77.082217),
                        new LatLng(-12.059467, -77.082224), new LatLng(-12.059491, -77.082352),
                        new LatLng(-12.059323, -77.082386), new LatLng(-12.059299, -77.082274),
                        new LatLng(-12.059288, -77.082272), new LatLng(-12.059276, -77.082220),
                        new LatLng(-12.059257, -77.082224))
                .strokeColor(Color.GREEN)
                .fillColor(Color.GREEN));
        //Facultad de Ciencias Matemáticas
        Polygon Fcm = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-12.060116, -77.082487), new LatLng(-12.060067, -77.082263),
                        new LatLng(-12.060175, -77.082238), new LatLng(-12.060191, -77.082309),
                        new LatLng(-12.060230, -77.082297), new LatLng(-12.060228, -77.082269),
                        new LatLng(-12.060266, -77.082258), new LatLng(-12.060258, -77.082204),
                        new LatLng(-12.060239, -77.082204), new LatLng(-12.060183, -77.081969),
                        new LatLng(-12.060117, -77.081981), new LatLng(-12.060122, -77.082012),
                        new LatLng(-12.060001, -77.082040), new LatLng(-12.059951, -77.081834),
                        new LatLng(-12.060067, -77.081805), new LatLng(-12.060073, -77.081822),
                        new LatLng(-12.060225, -77.081774), new LatLng(-12.060280, -77.081746),
                        new LatLng(-12.060291, -77.081786), new LatLng(-12.060327, -77.081780),
                        new LatLng(-12.060474, -77.082377), new LatLng(-12.060383, -77.082394),
                        new LatLng(-12.060349, -77.082297), new LatLng(-12.060324, -77.082303),
                        new LatLng(-12.060333, -77.082331), new LatLng(-12.060247, -77.082357),
                        new LatLng(-12.060241, -77.082345), new LatLng(-12.060205, -77.082351),
                        new LatLng(-12.060225, -77.082461))
                .strokeColor(Color.GREEN)
                .fillColor(Color.GREEN));
        //Facultad de Química e Ingeniería Química
        Polygon Fqiq = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-12.060175, -77.083928), new LatLng(-12.060140, -77.083820),
                        new LatLng(-12.060074, -77.083851), new LatLng(-12.060034, -77.083663),
                        new LatLng(-12.060090, -77.083657), new LatLng(-12.060023, -77.083384),
                        new LatLng(-12.060008, -77.083377), new LatLng(-12.059970, -77.083232),
                        new LatLng(-12.059977, -77.083214), new LatLng(-12.060253, -77.083134),
                        new LatLng(-12.060300, -77.083301), new LatLng(-12.060457, -77.083261),
                        new LatLng(-12.060609, -77.083227), new LatLng(-12.060617, -77.083265),
                        new LatLng(-12.060649, -77.083258), new LatLng(-12.060802, -77.083892),
                        new LatLng(-12.060770, -77.083903), new LatLng(-12.060783, -77.083963),
                        new LatLng(-12.060722, -77.083982), new LatLng(-12.060731, -77.084014),
                        new LatLng(-12.060776, -77.084008), new LatLng(-12.060787, -77.084062),
                        new LatLng(-12.060807, -77.084057), new LatLng(-12.060819, -77.084114),
                        new LatLng(-12.060741, -77.084136), new LatLng(-12.060733, -77.084107),
                        new LatLng(-12.060709, -77.084112), new LatLng(-12.060725, -77.084179),
                        new LatLng(-12.060609, -77.084212), new LatLng(-12.060570, -77.084060),
                        new LatLng(-12.060559, -77.083963), new LatLng(-12.060638, -77.083945),
                        new LatLng(-12.060550, -77.083539), new LatLng(-12.060486, -77.083554),
                        new LatLng(-12.060501, -77.083602), new LatLng(-12.060509, -77.083633),
                        new LatLng(-12.060500, -77.083651), new LatLng(-12.060480, -77.083664),
                        new LatLng(-12.060459, -77.083666), new LatLng(-12.060443, -77.083652),
                        new LatLng(-12.060437, -77.083637), new LatLng(-12.060424, -77.083569),
                        new LatLng(-12.060373, -77.083582), new LatLng(-12.060432, -77.083856))
                .strokeColor(Color.GREEN)
                .fillColor(Color.GREEN));


        //Facultad de Psicología
        Polygon Fp = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-12.053468, -77.087424), new LatLng(-12.053529, -77.086810),
                        new LatLng(-12.053682, -77.086825), new LatLng(-12.053656, -77.087036),
                        new LatLng(-12.053778, -77.087052), new LatLng(-12.053736, -77.087454))
                .strokeColor(Color.MAGENTA)
                .fillColor(Color.MAGENTA));
        //Facultad de Odontología
        Polygon Fco = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-12.054500, -77.085330), new LatLng(-12.054725, -77.085353),
                        new LatLng(-12.054689, -77.085683), new LatLng(-12.054660, -77.085685),
                        new LatLng(-12.054646, -77.085838), new LatLng(-12.054663, -77.085840),
                        new LatLng(-12.054635, -77.086146), new LatLng(-12.054340, -77.086123),
                        new LatLng(-12.054340, -77.086203), new LatLng(-12.054374, -77.086212),
                        new LatLng(-12.054374, -77.086263), new LatLng(-12.054421, -77.086269),
                        new LatLng(-12.054413, -77.086391), new LatLng(-12.054379, -77.086397),
                        new LatLng(-12.054374, -77.086414), new LatLng(-12.054212, -77.086385),
                        new LatLng(-12.054207, -77.086314), new LatLng(-12.054017, -77.086283),
                        new LatLng(-12.054001, -77.086226), new LatLng(-12.053962, -77.086214),
                        new LatLng(-12.053903, -77.086226), new LatLng(-12.053901, -77.086268),
                        new LatLng(-12.053764, -77.086254), new LatLng(-12.053818, -77.085714),
                        new LatLng(-12.053873, -77.085731), new LatLng(-12.053901, -77.085731),
                        new LatLng(-12.053957, -77.085768), new LatLng(-12.054268, -77.085802),
                        new LatLng(-12.054410, -77.085827), new LatLng(-12.054457, -77.085790),
                        new LatLng(-12.054471, -77.085731), new LatLng(-12.054487, -77.085734),
                        new LatLng(-12.054493, -77.085671), new LatLng(-12.054457, -77.085669))
                .strokeColor(Color.MAGENTA)
                .fillColor(Color.MAGENTA));


        //Facultad de Derecho y Ciencias Políticas
        Polygon Fdcp = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-12.058307, -77.080532), new LatLng(-12.058373, -77.080513),
                        new LatLng(-12.058352, -77.080365), new LatLng(-12.058316, -77.080372),
                        new LatLng(-12.058335, -77.080462), new LatLng(-12.058312, -77.080483),
                        new LatLng(-12.058272, -77.080471), new LatLng(-12.058252, -77.080379),
                        new LatLng(-12.058218, -77.080386), new LatLng(-12.058244, -77.080470),
                        new LatLng(-12.058241, -77.080493), new LatLng(-12.058084, -77.080526),
                        new LatLng(-12.058039, -77.080377), new LatLng(-12.058116, -77.080351),
                        new LatLng(-12.058102, -77.080307), new LatLng(-12.058032, -77.080318),
                        new LatLng(-12.057992, -77.080170), new LatLng(-12.058124, -77.080135),
                        new LatLng(-12.058140, -77.080172), new LatLng(-12.058181, -77.080163),
                        new LatLng(-12.058166, -77.080119), new LatLng(-12.058327, -77.080077),
                        new LatLng(-12.058438, -77.080048), new LatLng(-12.058581, -77.080607),
                        new LatLng(-12.058608, -77.080601), new LatLng(-12.058715, -77.081031),
                        new LatLng(-12.058451, -77.081103))
                .strokeColor(Color.RED)
                .fillColor(Color.RED));
        //Facultad de Ciencias Sociales
        Polygon Fcs = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-12.057811, -77.081819), new LatLng(-12.057799, -77.081762),
                        new LatLng(-12.057882, -77.081735), new LatLng(-12.057869, -77.081698),
                        new LatLng(-12.058139, -77.081636), new LatLng(-12.058113, -77.081476),
                        new LatLng(-12.058176, -77.081457), new LatLng(-12.058167, -77.081350),
                        new LatLng(-12.058317, -77.081311), new LatLng(-12.058356, -77.081469),
                        new LatLng(-12.058389, -77.081457), new LatLng(-12.058422, -77.081586),
                        new LatLng(-12.058410, -77.081587), new LatLng(-12.058425, -77.081663),
                        new LatLng(-12.058390, -77.081671), new LatLng(-12.058474, -77.082031),
                        new LatLng(-12.058459, -77.082038), new LatLng(-12.058467, -77.082074),
                        new LatLng(-12.058438, -77.082082), new LatLng(-12.058460, -77.082186),
                        new LatLng(-12.058296, -77.082221), new LatLng(-12.058285, -77.082240),
                        new LatLng(-12.058275, -77.082284), new LatLng(-12.058100, -77.082245),
                        new LatLng(-12.058097, -77.082130), new LatLng(-12.058051, -77.082134),
                        new LatLng(-12.058038, -77.082085), new LatLng(-12.058017, -77.082053),
                        new LatLng(-12.057992, -77.081972), new LatLng(-12.057982, -77.081902),
                        new LatLng(-12.057934, -77.081904), new LatLng(-12.057913, -77.081787))
                .strokeColor(Color.RED)
                .fillColor(Color.RED));

        //Facultad de Ciencias Administrativas
        Polygon Fca = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-12.057548, -77.081700), new LatLng(-12.057529, -77.081629),
                        new LatLng(-12.057512, -77.081628), new LatLng(-12.057504, -77.081584),
                        new LatLng(-12.057515, -77.081581), new LatLng(-12.057479, -77.081436),
                        new LatLng(-12.057811, -77.081354), new LatLng(-12.057857, -77.081523),
                        new LatLng(-12.057899, -77.081518), new LatLng(-12.057924, -77.081634),
                        new LatLng(-12.057732, -77.081686), new LatLng(-12.057722, -77.081659))
                .strokeColor(Color.YELLOW)
                .fillColor(Color.YELLOW));
        //Facultad de Ciencias Económicas
        Polygon Fce = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-12.057874, -77.081225), new LatLng(-12.057707, -77.080512),
                        new LatLng(-12.057923, -77.080459), new LatLng(-12.057972, -77.080610),
                        new LatLng(-12.058102, -77.080578), new LatLng(-12.058134, -77.080586),
                        new LatLng(-12.058160, -77.080604), new LatLng(-12.058168, -77.080629),
                        new LatLng(-12.058168, -77.080664), new LatLng(-12.058154, -77.080688),
                        new LatLng(-12.058128, -77.080701), new LatLng(-12.058090, -77.080700),
                        new LatLng(-12.058062, -77.080688), new LatLng(-12.058051, -77.080670),
                        new LatLng(-12.058028, -77.080672), new LatLng(-12.057991, -77.080683),
                        new LatLng(-12.058035, -77.080861), new LatLng(-12.058210, -77.080818),
                        new LatLng(-12.058283, -77.081121))
                .strokeColor(Color.YELLOW)
                .fillColor(Color.YELLOW));
        //Facultad de Ciencias Contables
        Polygon Fcc = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-12.057708, -77.080492), new LatLng(-12.057682, -77.080444),
                        new LatLng(-12.057608, -77.080455), new LatLng(-12.057597, -77.080406),
                        new LatLng(-12.057667, -77.080386), new LatLng(-12.057632, -77.080235),
                        new LatLng(-12.057871, -77.080177), new LatLng(-12.057919, -77.080444))
                .strokeColor(Color.YELLOW)
                .fillColor(Color.YELLOW));


        //Facultad de Letras y Ciencias Humanas
        Polygon Flch = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-12.056791, -77.081860), new LatLng(-12.056610, -77.081152),
                        new LatLng(-12.056677, -77.081133), new LatLng(-12.056654, -77.081067),
                        new LatLng(-12.056696, -77.081051), new LatLng(-12.056716, -77.081125),
                        new LatLng(-12.056771, -77.081110), new LatLng(-12.056880, -77.081543),
                        new LatLng(-12.056963, -77.081522), new LatLng(-12.056912, -77.081321),
                        new LatLng(-12.057062, -77.081281), new LatLng(-12.057094, -77.081387),
                        new LatLng(-12.057137, -77.081377), new LatLng(-12.056985, -77.080760),
                        new LatLng(-12.057141, -77.080719), new LatLng(-12.057151, -77.080767),
                        new LatLng(-12.057240, -77.080749), new LatLng(-12.057370, -77.081312),
                        new LatLng(-12.057401, -77.081305), new LatLng(-12.057486, -77.081688),
                        new LatLng(-12.057383, -77.081717), new LatLng(-12.057377, -77.081704))
                .strokeColor(Color.GRAY)
                .fillColor(Color.GRAY));
        //Facultad de Educación
        Polygon Fe1 = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-12.054633, -77.084876), new LatLng(-12.054678, -77.084796),
                        new LatLng(-12.054740, -77.084828), new LatLng(-12.054834, -77.084663),
                        new LatLng(-12.054727, -77.084598), new LatLng(-12.054803, -77.084485),
                        new LatLng(-12.054962, -77.084584), new LatLng(-12.054746, -77.084952))
                .strokeColor(Color.GRAY)
                .fillColor(Color.GRAY));
        Polygon Fe2 = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-12.055429, -77.085175), new LatLng(-12.055607, -77.084888),
                        new LatLng(-12.055971, -77.085130), new LatLng(-12.055859, -77.085333),
                        new LatLng(-12.055633, -77.085202), new LatLng(-12.055584, -77.085273))
                .strokeColor(Color.GRAY)
                .fillColor(Color.GRAY));


        //Facultad de Ingeniería Electrónica y Eléctrica
        Polygon Fiee1 = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-12.054902, -77.087406), new LatLng(-12.054946, -77.086797),
                        new LatLng(-12.055598, -77.086849), new LatLng(-12.055530, -77.087444))
                .strokeColor(Color.BLUE)
                .fillColor(Color.BLUE));
        Polygon Fiee2 = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-12.056179, -77.082123), new LatLng(-12.056158, -77.081999),
                        new LatLng(-12.056243, -77.081976), new LatLng(-12.056236, -77.081946),
                        new LatLng(-12.056254, -77.081905), new LatLng(-12.056392, -77.081864),
                        new LatLng(-12.056403, -77.081887), new LatLng(-12.056423, -77.081882),
                        new LatLng(-12.056423, -77.081871), new LatLng(-12.056439, -77.081864),
                        new LatLng(-12.056445, -77.081882), new LatLng(-12.056474, -77.081871),
                        new LatLng(-12.056434, -77.081720), new LatLng(-12.056412, -77.081714),
                        new LatLng(-12.056385, -77.081623), new LatLng(-12.056416, -77.081591),
                        new LatLng(-12.056385, -77.081532), new LatLng(-12.056351, -77.081541),
                        new LatLng(-12.056336, -77.081566), new LatLng(-12.056318, -77.081582),
                        new LatLng(-12.056283, -77.081584), new LatLng(-12.056258, -77.081568),
                        new LatLng(-12.056234, -77.081561), new LatLng(-12.056216, -77.081518),
                        new LatLng(-12.056118, -77.081534), new LatLng(-12.056098, -77.081459),
                        new LatLng(-12.056134, -77.081443), new LatLng(-12.056116, -77.081330),
                        new LatLng(-12.056245, -77.081316), new LatLng(-12.056231, -77.081223),
                        new LatLng(-12.056318, -77.081193), new LatLng(-12.056360, -77.081359),
                        new LatLng(-12.056540, -77.081332), new LatLng(-12.056607, -77.081591),
                        new LatLng(-12.056574, -77.081602), new LatLng(-12.056594, -77.081687),
                        new LatLng(-12.056630, -77.081678), new LatLng(-12.056659, -77.081812),
                        new LatLng(-12.056630, -77.081821), new LatLng(-12.056637, -77.081847),
                        new LatLng(-12.056668, -77.081846), new LatLng(-12.056695, -77.081975),
                        new LatLng(-12.056668, -77.081979), new LatLng(-12.056643, -77.081917),
                        new LatLng(-12.056534, -77.081933), new LatLng(-12.056548, -77.082014),
                        new LatLng(-12.056432, -77.082048), new LatLng(-12.056427, -77.082027),
                        new LatLng(-12.056293, -77.082060), new LatLng(-12.056293, -77.082089))
                .strokeColor(Color.BLUE)
                .fillColor(Color.BLUE));
        //Facultad de Ingeniería de Sitemas e Informática
        Polygon Fisi = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-12.053168, -77.085931), new LatLng(-12.053140, -77.085731),
                        new LatLng(-12.053199, -77.085709), new LatLng(-12.053261, -77.085714),
                        new LatLng(-12.053191, -77.085149), new LatLng(-12.053176, -77.085122),
                        new LatLng(-12.053193, -77.085084), new LatLng(-12.053417, -77.085065),
                        new LatLng(-12.053442, -77.085228), new LatLng(-12.053499, -77.085221),
                        new LatLng(-12.053507, -77.085295), new LatLng(-12.053441, -77.085302),
                        new LatLng(-12.053500, -77.085716), new LatLng(-12.053566, -77.085710),
                        new LatLng(-12.053586, -77.085875), new LatLng(-12.053168, -77.085930))
                .strokeColor(Color.BLUE)
                .fillColor(Color.BLUE));
        //Facultad de Ingeniería Industrial
        Polygon Fii = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-12.059535, -77.081038), new LatLng(-12.059578, -77.081024),
                        new LatLng(-12.059546, -77.080939), new LatLng(-12.059506, -77.080961),
                        new LatLng(-12.059505, -77.080983), new LatLng(-12.059474, -77.080988),
                        new LatLng(-12.059453, -77.080935), new LatLng(-12.059455, -77.080883),
                        new LatLng(-12.059446, -77.080837), new LatLng(-12.059448, -77.080816),
                        new LatLng(-12.059455, -77.080771), new LatLng(-12.059553, -77.080700),
                        new LatLng(-12.059711, -77.080645), new LatLng(-12.059763, -77.080641),
                        new LatLng(-12.059776, -77.080686), new LatLng(-12.060117, -77.080604),
                        new LatLng(-12.060194, -77.080938), new LatLng(-12.059823, -77.081023),
                        new LatLng(-12.059809, -77.080971), new LatLng(-12.059732, -77.080989),
                        new LatLng(-12.059815, -77.081344), new LatLng(-12.060195, -77.081253),
                        new LatLng(-12.060189, -77.081239), new LatLng(-12.060138, -77.081249),
                        new LatLng(-12.060119, -77.081190), new LatLng(-12.060105, -77.081190),
                        new LatLng(-12.060088, -77.081135), new LatLng(-12.060101, -77.081121),
                        new LatLng(-12.060097, -77.081108), new LatLng(-12.060122, -77.081009),
                        new LatLng(-12.060160, -77.081015), new LatLng(-12.060188, -77.081032),
                        new LatLng(-12.060213, -77.081056), new LatLng(-12.060226, -77.081080),
                        new LatLng(-12.060299, -77.081359), new LatLng(-12.060255, -77.081376),
                        new LatLng(-12.060261, -77.081415), new LatLng(-12.059901, -77.081496),
                        new LatLng(-12.059877, -77.081434), new LatLng(-12.059782, -77.081465),
                        new LatLng(-12.059776, -77.081455), new LatLng(-12.059727, -77.081461),
                        new LatLng(-12.059714, -77.081445), new LatLng(-12.059642, -77.081455))
                .strokeColor(Color.BLUE)
                .fillColor(Color.BLUE));
        //Facultad de Ingeniería Geológica, Minera, Metalúrgica y Geográfica
        Polygon Figmmg1 = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-12.060347, -77.084660), new LatLng(-12.060286, -77.084462),
                        new LatLng(-12.060427, -77.084421), new LatLng(-12.060565, -77.084409),
                        new LatLng(-12.060695, -77.084371), new LatLng(-12.060715, -77.084432),
                        new LatLng(-12.060754, -77.084424), new LatLng(-12.060798, -77.084629),
                        new LatLng(-12.060770, -77.084639), new LatLng(-12.060779, -77.084709),
                        new LatLng(-12.060764, -77.084714), new LatLng(-12.060775, -77.084773),
                        new LatLng(-12.060753, -77.084781), new LatLng(-12.060869, -77.085317),
                        new LatLng(-12.060756, -77.085341), new LatLng(-12.060723, -77.085207),
                        new LatLng(-12.060739, -77.085194), new LatLng(-12.060711, -77.085095),
                        new LatLng(-12.060654, -77.085113), new LatLng(-12.060633, -77.085073),
                        new LatLng(-12.060697, -77.085047), new LatLng(-12.060636, -77.084773),
                        new LatLng(-12.060516, -77.084799), new LatLng(-12.060532, -77.084878),
                        new LatLng(-12.060415, -77.084910), new LatLng(-12.060356, -77.084719),
                        new LatLng(-12.060480, -77.084685), new LatLng(-12.060502, -77.084750),
                        new LatLng(-12.060550, -77.084741), new LatLng(-12.060544, -77.084717),
                        new LatLng(-12.060559, -77.084697), new LatLng(-12.060541, -77.084597),
                        new LatLng(-12.060579, -77.084577), new LatLng(-12.060571, -77.084508),
                        new LatLng(-12.060457, -77.084543), new LatLng(-12.060472, -77.084636))
                .strokeColor(Color.BLUE)
                .fillColor(Color.BLUE));
        Polygon Figmmg2 = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-12.060253, -77.084242), new LatLng(-12.060229, -77.084167),
                        new LatLng(-12.060282, -77.084151), new LatLng(-12.060284, -77.084140),
                        new LatLng(-12.060460, -77.084095), new LatLng(-12.060452, -77.084055),
                        new LatLng(-12.060423, -77.084063), new LatLng(-12.060407, -77.084015),
                        new LatLng(-12.060370, -77.084026), new LatLng(-12.060381, -77.084082),
                        new LatLng(-12.060342, -77.084092), new LatLng(-12.060326, -77.084042),
                        new LatLng(-12.060300, -77.084092), new LatLng(-12.060271, -77.084103),
                        new LatLng(-12.060270, -77.084124), new LatLng(-12.060228, -77.084131),
                        new LatLng(-12.060186, -77.083970), new LatLng(-12.060499, -77.083887),
                        new LatLng(-12.060577, -77.084157))
                .strokeColor(Color.BLUE)
                .fillColor(Color.BLUE));

        Polygon Figmmg3 = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-12.051988, -77.087375), new LatLng(-12.051993, -77.087269),
                        new LatLng(-12.052030, -77.087268), new LatLng(-12.052043, -77.087150),
                        new LatLng(-12.052134, -77.087161), new LatLng(-12.052126, -77.087305),
                        new LatLng(-12.052230, -77.087317), new LatLng(-12.052241, -77.087254),
                        new LatLng(-12.052408, -77.087258), new LatLng(-12.052396, -77.087410))
                .strokeColor(Color.BLUE)
                .fillColor(Color.BLUE));
        Polygon Figmmg41 = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-12.055025, -77.086660), new LatLng(-12.055068, -77.086224),
                        new LatLng(-12.055158, -77.086229), new LatLng(-12.055122, -77.086632),
                        new LatLng(-12.055108, -77.086629), new LatLng(-12.055103, -77.086665))
                .strokeColor(Color.BLUE)
                .fillColor(Color.BLUE));

        Polygon Figmmg42 = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-12.055101, -77.086207), new LatLng(-12.055051, -77.086205),
                        new LatLng(-12.055066, -77.086054), new LatLng(-12.055386, -77.086077),
                        new LatLng(-12.055389, -77.086149), new LatLng(-12.055118, -77.086126))
                .strokeColor(Color.BLUE)
                .fillColor(Color.BLUE));

        Polygon Figmmg43 = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-12.055147, -77.086604), new LatLng(-12.055158, -77.086514),
                        new LatLng(-12.055395, -77.086543), new LatLng(-12.055386, -77.086636))
                .strokeColor(Color.BLUE)
                .fillColor(Color.BLUE));

        Polygon Figmmg44 = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-12.055496, -77.086138), new LatLng(-12.055509, -77.086042),
                        new LatLng(-12.055836, -77.086083), new LatLng(-12.055842, -77.086119),
                        new LatLng(-12.055888, -77.086126), new LatLng(-12.055876, -77.086258),
                        new LatLng(-12.055859, -77.086260), new LatLng(-12.055803, -77.086691),
                        new LatLng(-12.055699, -77.086677), new LatLng(-12.055754, -77.086174))
                .strokeColor(Color.BLUE)
                .fillColor(Color.BLUE));

        /*mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition position) {
                BigDecimal bigDecimal = new BigDecimal(Double.toString(Math.pow((position.target.latitude + 12.056131), 2) + Math.pow((position.target.longitude + 77.084369), 2)));
                if (position.zoom < DEFAULT_ZOOM) {
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(DEFAULT_ZOOM));
                }
                if(bigDecimal.doubleValue() > 0.0000395) {
                    googleMap.getUiSettings().setScrollGesturesEnabled(false);
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng((position.target.latitude - 12.056131) / 2, (position.target.longitude - 77.084369) / 2)));
                }
                else
                    googleMap.getUiSettings().setScrollGesturesEnabled(true);
            }
        });*/
    }

    public void checkBounds() {

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(new LatLng(-12.050964, -77.089161));
        builder.include(new LatLng(-12.061581, -77.089161));
        builder.include(new LatLng(-12.061581, -77.079002));
        builder.include(new LatLng(-12.050964, -77.079002));
        final LatLngBounds allowedBounds = builder.build();
        LatLngBounds centro = mMap.getProjection().getVisibleRegion().latLngBounds;
        if (allowedBounds.contains(centro.getCenter()))
            return;
        else {
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(unmsm, DEFAULT_ZOOM));
            //mMap.moveCamera(CameraUpdateFactory.newLatLng(unmsm));
        }
    }

}
