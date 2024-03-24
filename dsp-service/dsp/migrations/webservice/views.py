from rest_framework.views import APIView
from rest_framework import status
from django.http import JsonResponse
import json

from dsp.migrations.service.dft import DFT
from dsp.migrations.service.fft import FFT
from dsp.migrations.service.nsp import NSP


# Create your views here.
class TimeFftView(APIView):

    def post(self, request):
        svc = FFT(request.data['input'])
        matrix = svc.calc_time_matrix()
        res = svc.calc_time_fft()
        return JsonResponse({
            'listResult': res,
            'matrix': matrix
        }, status=status.HTTP_200_OK)


class FreqFftView(APIView):

    def post(self, request):
        svc = FFT(request.data['input'])
        matrix = svc.calc_freq_matrix()
        res = svc.calc_freq_fft()
        return JsonResponse({
            'listResult': res,
            'matrix': matrix
        }, status=status.HTTP_200_OK)


class DftView(APIView):

    def post(self, request):
        svc = DFT(request.data["a1"], request.data["om1"], request.data["a2"], request.data["om2"])
        type_filter = request.data["type"]
        res = {}
        if type_filter == 0:
            res = svc.low_pass_filter()
        if type_filter == 1:
            res = svc.high_pass_filter()
        return JsonResponse({
            'listResult': res['imageList'],
            'alpha': res['alpha'],
            'beta': res['beta']
        }, status=status.HTTP_200_OK)


class NspView(APIView):
    def post(self, request):
        svc = NSP(request.data["frequency"], request.data["noiseFrequency"], request.data["numberOfSamples"], request.data["samplingRate"])
        image_url = svc.calc()
        return JsonResponse({
                'listResult': image_url
        }, status=status.HTTP_200_OK)
